package com.example.ems.repository;

import com.example.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // derived query methods
    Optional<Department> findByName(String name);

    List<Department> findByNameContainingIgnoreCase(String keyword);

}
