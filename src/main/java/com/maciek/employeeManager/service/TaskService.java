package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task findById(int theId);
    List<Task> showAllTasksForCurrentEmployee(Employee employee);
    void saveTask(Task task);
    void deleteTaskById(int taskId);
//    List<String> getEmployeesNamesForCurrentTask(Task task);
}
