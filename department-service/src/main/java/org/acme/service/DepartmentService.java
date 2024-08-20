package org.acme.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.acme.model.Department;
import org.acme.repository.DepartmentRepository;
import java.util.List;

@ApplicationScoped
public class DepartmentService {

    @Inject
    DepartmentRepository departmentRepository;

    @Inject
    EntityManager entityManager;

    @PostConstruct
    public void initialize() {//init auto the procedures to use query
        departmentRepository.createStoredProcedure();
    }

    @Transactional
    public Department createDepartment(Department department) {
        departmentRepository.persist(department);
        return department;
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.listAll();
    }

    @Transactional
    public Department getDepartmentByName(String name) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_department_by_name")
                .registerStoredProcedureParameter("p_name", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_id", Long.class, jakarta.persistence.ParameterMode.OUT)
                .registerStoredProcedureParameter("p_department_name", String.class, jakarta.persistence.ParameterMode.OUT)
                .setParameter("p_name", name);
        //p_name=name -----> get the name I want

        query.execute(); //to generate query

        Long id = (Long) query.getOutputParameterValue("p_id");
        String departmentName = (String) query.getOutputParameterValue("p_department_name");

        if (id == null || departmentName == null) {
            return null; // no dep found
        }

        return Department
                .builder()
                .id(id)
                .name(departmentName)
                .build();
    }
}
