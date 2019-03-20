package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(int theId);
    List<Task> showAllTasksForCurrentEmployee(Employee employee);
}
