package com.maciek.employeeManager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST,CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name="task_employee",
            joinColumns = @JoinColumn(name="task_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    //JsonIgnore also works
    @JsonBackReference
    private List<Employee> employees;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return title;
    }
}
