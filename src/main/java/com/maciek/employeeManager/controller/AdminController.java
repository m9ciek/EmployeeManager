package com.maciek.employeeManager.controller;


import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.service.EmployeeService;
import com.maciek.employeeManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private EmployeeService employeeService;
    private TaskService taskService;

    @Autowired
    public AdminController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping("/employees")
    public String showEmployees(Model model){
        List<Task> tasks;
        List<Employee> employees = employeeService.findAll();
        for(Employee e : employees){
            taskService.showAllTasksForCurrentEmployee(e);
        }
        model.addAttribute("employees", employees);
        return "employees-list";
    }

    @GetMapping("/employee-form")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/tasks")
    public String showTasks(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/admin/employees";
    }
}
