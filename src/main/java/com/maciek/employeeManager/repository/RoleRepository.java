package com.maciek.employeeManager.repository;

import com.maciek.employeeManager.entity.Role;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

//Testing Hibernate without Spring Data JPA
@Repository
public class RoleRepository {

    private EntityManager entityManager;

    @Autowired
    public RoleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Role role){
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(role);
    }

    public void deleteRoleByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Role role = currentSession.get(Role.class, email);
        currentSession.delete(role);
    }

    public Role findRoleForEmployeeEmail(String email){
        Session currentSession = entityManager.unwrap(Session.class);
        Role role = currentSession.get(Role.class, email);
        return role;
    }
}
