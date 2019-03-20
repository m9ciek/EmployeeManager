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

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private TaskService taskService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showActiveTasks(Model model, Principal principal){
        String employeeEmail = principal.getName();
        Employee employee = employeeRepository.findByEmail(employeeEmail);
        List<Task> tasks = taskService.showAllTasksForCurrentEmployee(employee);
        model.addAttribute("currentTasks", tasks);
        model.addAttribute("principal", employeeEmail);
        return "index";
    }

    @GetMapping("/tasks")
    public String showAllTasks(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @GetMapping("/add-task")
    public String showFormForAddingTask(@RequestParam int taskId, Principal principal){
        Employee employee = employeeRepository.findByEmail(principal.getName());
        Task task = taskService.findById(taskId);
        employeeService.addTaskToEmployee(employee,task);
        return "redirect:";
    }

}
