package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee employee;
        if(result.isPresent()){
            employee = result.get();
        }
        else{
            throw new RuntimeException("Employee with id: " + theId +" doesn't exist.");
        }
        return employee;
    }

    @Override
    public void addTaskToEmployee(Employee employee, Task task) {
        List<Task> employeeCurrentTasks = employee.getTasks();
        if(!employeeCurrentTasks.contains(task)){
            employeeCurrentTasks.add(taskService.findById(task.getId()));
            employee.setTasks(employeeCurrentTasks);

            employeeRepository.save(employee);
        }
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}
