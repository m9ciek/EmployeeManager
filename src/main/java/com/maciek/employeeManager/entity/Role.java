package com.maciek.employeeManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_role")
public class Role {

    @Id
    @Column(name="employee_email")
    private String email;
    @Column(name = "role")
    private String role;

    public Role(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public Role() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
