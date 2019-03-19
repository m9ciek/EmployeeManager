package com.maciek.employeeManager.controller;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.repository.EmployeeRepository;
import com.maciek.employeeManager.service.EmployeeService;
import com.maciek.employeeManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showAllTasks(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @GetMapping("/showForm")
    public String showFormForAddingTask(@RequestParam int taskId, Model model){
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "show-form";
    }

    @PostMapping("/tasks")
    public String addExistingTaskToEmployee(@ModelAttribute("task") Task task, Principal principal){
        int taskId = task.getId();
        Employee employee = employeeRepository.findByEmail(principal.getName());
        Task tempTask = taskService.findById(taskId);
        employeeService.addTaskToEmployee(employee,tempTask);
        return "task";
    }
}
