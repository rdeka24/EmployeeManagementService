package com.Employee.EmployeeManagementService.controller;

import com.Employee.EmployeeManagementService.model.Employee;
import com.Employee.EmployeeManagementService.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @PostMapping("/add")
    public Employee createEmployee(@Valid @RequestBody  Employee employee) {
        return employeeRepository.save(employee);
       }
    @GetMapping("/{id}")
        public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            Optional<Employee> employee = employeeRepository.findById(id);
            if(employee.isPresent()) {
                return ResponseEntity.ok(employee.get() );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the employee: " + e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
