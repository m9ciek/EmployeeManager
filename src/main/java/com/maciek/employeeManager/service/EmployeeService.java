package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
}
