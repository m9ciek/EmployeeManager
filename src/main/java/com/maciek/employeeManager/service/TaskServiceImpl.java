package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);
        Task task;
        if(result.isPresent()){
            task = result.get();
        }else {
            throw new RuntimeException("No Task found");
        }
        return task;
    }

    @Override
    public List<Task> showAllTasksForCurrentEmployee(Employee employee) {
        return employee.getTasks();
    }
}
