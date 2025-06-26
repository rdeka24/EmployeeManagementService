package com.Employee.EmployeeManagementService.controller;

import com.Employee.EmployeeManagementService.model.Employee;
import com.Employee.EmployeeManagementService.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

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
    @Test
    public void testCreateEmployee_Success() {
        // Arrange
        Employee inputEmployee = new Employee();
        inputEmployee.setEmpName("John Doe");
        inputEmployee.setEmpSal(50000.0);
        inputEmployee.setEmpAddress("New York");

        Employee savedEmployee = new Employee();
        savedEmployee.setEmpId(1);
        savedEmployee.setEmpName("John Doe");
        savedEmployee.setEmpSal(50000.0);
        savedEmployee.setEmpAddress("New York");

        when(employeeRepository.save(inputEmployee)).thenReturn(savedEmployee);

        // Act
        Employee result = employeeController.createEmployee(inputEmployee);

        // Assert
        assertEquals(savedEmployee.getEmpId(), result.getEmpId());
        assertEquals("John Doe", result.getEmpName());
        assertEquals(50000, result.getEmpSal());
        assertTrue(result.getEmpAddress().contains("New York"));
    }

    @Test
    public void testGetById_EmployeeFound() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpName("Alice");
        employee.setEmpSal(60000.0);
        employee.setEmpAddress("Mumbai");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        ResponseEntity<?> response = employeeController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Employee);
        assertEquals("Alice", ((Employee) response.getBody()).getEmpName());
    }

    @Test
    public void testGetById_EmployeeNotFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = employeeController.getById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Employee not found with id: 2"));
    }

    @Test
    public void testGetById_ExceptionThrown() {
        when(employeeRepository.findById(3L)).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<?> response = employeeController.getById(3L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("An error occurred while retrieving the employee"));
    }
    @Test
    public void testDeleteUser() {
        Long idToDelete = 5L;

        // Act
        employeeController.deleteUser(idToDelete);

        // Assert using Mockito verify
        verify(employeeRepository, times(1)).deleteById(idToDelete);

        // Assert true that deleteById was called once with correct ID
        assertTrue(true, "deleteById was called successfully");

        // Optional: Dummy assertion to show it was executed
        assertEquals(5L, idToDelete, "ID should match the one passed to deleteUser");
    }

}
