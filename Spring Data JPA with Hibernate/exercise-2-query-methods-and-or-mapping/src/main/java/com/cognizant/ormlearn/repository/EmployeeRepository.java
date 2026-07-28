package com.cognizant.ormlearn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Fetch join for department and skillList in a single query, used
    // whenever full employee detail is needed
    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.skillList WHERE e.id = :id")
    Optional<Employee> findByIdWithDetails(@Param("id") int id);
}
