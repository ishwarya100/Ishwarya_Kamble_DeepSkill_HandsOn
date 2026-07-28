package com.cognizant.ormlearn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Fetch join so employeeList is populated in the same query, avoiding
    // LazyInitializationException once the session is closed
    @Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employeeList WHERE d.id = :id")
    Optional<Department> findByIdWithEmployees(@Param("id") int id);
}
