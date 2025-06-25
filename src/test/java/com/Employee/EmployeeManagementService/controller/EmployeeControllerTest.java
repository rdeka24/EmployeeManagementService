package com.Employee.EmployeeManagementService.controller;

import com.Employee.EmployeeManagementService.model.Employee;
import com.Employee.EmployeeManagementService.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    private EmployeeRepository employeeRepository;
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeController = new EmployeeController(employeeRepository);
    }
    // Test Case 1: No employees returned
    @Test
    public void testGetAllEmployees_EmptyList() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Employee> result = employeeController.getAllEmployees();

        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }
    @Test
    public void testGetAllEmployees_Success() {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, "John Doe", 50000.0, "Mumbai"),
                new Employee(2, "Jane Smith", 60000.0, "Pune")
        );

        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> result = employeeController.getAllEmployees();

        assertFalse(result.isEmpty(), "Expected employee list to not be empty");
        assertEquals(2, result.size(), "Expected list size to be 2");
        assertEquals("John Doe", result.get(0).getEmpName());
        assertEquals("Pune", result.get(1).getEmpAddress());
    }
}
