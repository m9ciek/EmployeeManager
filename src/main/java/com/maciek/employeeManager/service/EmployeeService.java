package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    void addTaskToEmployee(Employee employee, Task task);
    void save(Employee employee);
}
