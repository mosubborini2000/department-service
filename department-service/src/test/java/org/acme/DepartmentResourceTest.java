package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.model.Department;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class DepartmentResourceTest {

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setName("Finance");

        given()
          .body(department)
          .header("Content-Type", "application/json")
          .when()
          .post("/departments")
          .then()
             .statusCode(200)
             .body("id", notNullValue())
             .body("name", is("Finance"));
    }
}