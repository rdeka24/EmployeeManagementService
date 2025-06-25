package com.Employee.EmployeeManagementService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @NotBlank(message = "Employee name is mandatory")
    private String empName;
    @NotNull(message = "Employee Salary is mandatory")
    private Double empSal;
    @NotBlank(message = "Employee address is mandatory")
    private String empAddress;

    public Employee() {
    }

    public Employee(int empId, String empName, Double empSal, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empSal = empSal;
        this.empAddress = empAddress;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSal() {
        return empSal;
    }

    public void setEmpSal(Double empSal) {
        this.empSal = empSal;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName=" + empName +
                ", empSal=" + empSal +
                ", empAddress=" + empAddress +
                '}';
    }
}
