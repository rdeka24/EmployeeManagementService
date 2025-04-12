package com.Employee.EmployeeManagementService.model;

public class Employee {
    private int empId;
    private int empName;
    private int empSal;
    private int empAddress;


    public Employee(int empId, int empName, int empSal, int empAddress) {
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

    public int getEmpName() {
        return empName;
    }

    public void setEmpName(int empName) {
        this.empName = empName;
    }

    public int getEmpSal() {
        return empSal;
    }

    public void setEmpSal(int empSal) {
        this.empSal = empSal;
    }

    public int getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(int empAddress) {
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
