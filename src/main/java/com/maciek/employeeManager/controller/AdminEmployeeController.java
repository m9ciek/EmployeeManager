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
public class AdminEmployeeController {

    private EmployeeService employeeService;
    private TaskService taskService;

    @Autowired
    public AdminEmployeeController(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    @GetMapping("/employees")
    public String showEmployees(Model model){

        List<Employee> employees = employeeService.findAllEmployees();

        model.addAttribute("employees", employees);
        return "employees-list";
    }

    @GetMapping("/employee-form")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.saveEmployee(theEmployee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employeeId") int theId){
        employeeService.deleteEmployee(theId);
        return "redirect:/admin/employees";
    }

    //need to fix updating email, throws exception
    @GetMapping("/update-employee")
    public String updateEmployee(@RequestParam("employeeId") int theId, Model model){
        Employee employee = employeeService.findEmployeesById(theId);
        model.addAttribute(employee);
        return "employee-form";
    }
}
