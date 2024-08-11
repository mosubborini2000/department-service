package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Department;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {
}