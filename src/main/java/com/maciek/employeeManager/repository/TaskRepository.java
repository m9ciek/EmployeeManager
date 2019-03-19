package com.maciek.employeeManager.repository;

import com.maciek.employeeManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Task findByTitle(String title);
}
