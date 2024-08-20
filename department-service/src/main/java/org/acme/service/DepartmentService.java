package org.acme.service;

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
    public Department getDepartmentByName(String name) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getDepartmentByName");
        query.setParameter("p_name", name);

        query.execute();

        Long id = (Long) query.getOutputParameterValue("p_id");
        String departmentName = (String) query.getOutputParameterValue("p_department_name");

        return Department
                .builder()
                .id(id)
                .name(departmentName)
                .build();
    }
}