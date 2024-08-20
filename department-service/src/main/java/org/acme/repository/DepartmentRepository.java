package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Department;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createStoredProcedure() {
        String createProcedureSQL = """
            CREATE OR REPLACE PROCEDURE get_department_by_name (
               p_name IN VARCHAR2,
               p_id OUT NUMBER,
               p_department_name OUT VARCHAR2
            ) AS
            BEGIN
               SELECT id, name INTO p_id, p_department_name
               FROM department
               WHERE name = p_name;
            END;
            """;

        entityManager.createNativeQuery(createProcedureSQL).executeUpdate();
    }
}
