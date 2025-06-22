package com.Employee.EmployeeManagementService.controller;

import com.Employee.EmployeeManagementService.model.Employee;
import com.Employee.EmployeeManagementService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
       }
    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
