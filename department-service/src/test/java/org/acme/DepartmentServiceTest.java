package org.acme;
import org.acme.model.Department;
import org.acme.repository.DepartmentRepository;
import org.acme.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;
    @Test
    public void testCreateDepartmment(){
        Department department = new Department();
        department.setName("IT");

        doNothing().when(departmentRepository).persist(department);
        Department result = departmentService.createDepartment(department);

        assertEquals("IT", result.getName());
        verify(departmentRepository).persist(department);
    }
    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentRepository.findById(1L)).thenReturn(department);

        Department result = departmentService.getDepartmentById(1L);

        assertEquals("HR", result.getName());
    }
    @Test
    public void testGetAllDepartments() {
        Department department1 = new Department();
        department1.setName("HR");
        Department department2 = new Department();
        department2.setName("IT");

        when(departmentRepository.listAll()).thenReturn(List.of(department1, department2));

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
    }
}