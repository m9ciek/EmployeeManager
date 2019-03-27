package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();
    Employee findEmployeesById(int theId);
    void addTaskToEmployee(Employee employee, Task taskToAdd);
    void saveEmployee(Employee employeeToSave);
    void deleteEmployee(int id);
}
