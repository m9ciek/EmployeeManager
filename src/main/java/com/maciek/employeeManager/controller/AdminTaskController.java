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
public class AdminTaskController {

    private TaskService taskService;

    private EmployeeService employeeService;

    @Autowired
    public AdminTaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping("/tasks")
    public String showTasks(Model model){
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }

    @GetMapping("/task-form")
    public String showFormForAdd(Model model){
        Task task = new Task();
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task){
        taskService.saveTask(task);
        return "redirect:/admin/tasks";
    }

    @GetMapping("/update-task")
    public String updateTask(@RequestParam("taskId") int theId, Model model){
        Task task = taskService.findById(theId);
        model.addAttribute("task", task);
        return "task-form";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") int theId){
        taskService.deleteTaskById(theId);
        return "redirect:/admin/tasks";
    }
}
