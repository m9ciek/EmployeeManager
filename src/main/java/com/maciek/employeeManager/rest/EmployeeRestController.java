package com.maciek.employeeManager.rest;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.findEmployeesById(employeeId));
    }
}
