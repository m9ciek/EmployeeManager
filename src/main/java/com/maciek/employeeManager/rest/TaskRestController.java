package com.maciek.employeeManager.rest;

import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> findAll(){
        return taskService.findAll();
    }

}
