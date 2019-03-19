package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
}
