package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.Department;
import org.acme.repository.DepartmentRepository;

import java.util.List;

@ApplicationScoped
public class DepartmentService {
    @Inject
    DepartmentRepository departmentRepository;
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
}