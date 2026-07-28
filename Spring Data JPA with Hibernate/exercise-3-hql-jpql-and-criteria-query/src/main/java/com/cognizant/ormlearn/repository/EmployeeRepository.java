package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // HQL looks like SQL, but instead of tables, Java classes and their
    // instance variables are addressed here
    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // Average salary across all employees
    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    // Average salary filtered by department id
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // Native query - direct SQL instead of HQL, kept minimal as per guidance
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
