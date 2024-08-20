package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Department;
import org.acme.service.DepartmentService;
import java.util.List;

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    DepartmentService departmentService;

    @POST
    public Department createDepartment(Department department) {
        return departmentService.createDepartment(department);
    }

    @GET
    @Path("/{id}")
    public Department getDepartmentById(@PathParam("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GET
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    @GET
    @Path("/name/{name}")
    public Department getDepartmentByName(@PathParam("name") String name) {
        return departmentService.getDepartmentByName(name);
        //put the dep name in postman
    }

}