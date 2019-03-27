package com.maciek.employeeManager.service;

import com.maciek.employeeManager.entity.Employee;
import com.maciek.employeeManager.entity.Role;
import com.maciek.employeeManager.entity.Task;
import com.maciek.employeeManager.repository.EmployeeRepository;
import com.maciek.employeeManager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskService taskService;

    @Override
    @Transactional
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee findEmployeesById(int theId) {
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
    @Transactional
    public void addTaskToEmployee(Employee employee, Task taskToAdd) {
        List<Task> employeeCurrentTasks = employee.getTasks();
        if(!employeeCurrentTasks.contains(taskToAdd)){
            employeeCurrentTasks.add(taskService.findById(taskToAdd.getId()));
            employee.setTasks(employeeCurrentTasks);

            employeeRepository.save(employee);
        }
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employeeToSave) {
        String email = employeeToSave.getEmail();
        employeeRepository.save(employeeToSave);
        if(roleRepository.findRoleForEmployeeEmail(email)== null){
            Role role = new Role(email, "ROLE_USER");
            roleRepository.save(role);
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Employee employee = this.findEmployeesById(id);
        roleRepository.deleteRoleByEmail(employee.getEmail());
        employeeRepository.deleteById(id);
    }
}
