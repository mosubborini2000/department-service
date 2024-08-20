package org.acme.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQuery(
        name = "getDepartmentByName",
        procedureName = "get_department_by_name",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_department_name", type = String.class)
        },
        resultClasses = Department.class
)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}