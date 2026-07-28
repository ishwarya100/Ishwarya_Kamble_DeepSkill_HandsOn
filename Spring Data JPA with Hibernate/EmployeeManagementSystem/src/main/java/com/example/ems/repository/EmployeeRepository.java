package com.example.ems.repository;

import com.example.ems.entity.Employee;
import com.example.ems.projection.EmployeeNameEmailProjection;
import com.example.ems.projection.EmployeeSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // derived query methods, built from the method name
    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentId(Long departmentId);

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    // resolved automatically to the named query declared on Employee
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // custom query using @Query and a bind parameter
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId ORDER BY e.name ASC")
    List<Employee> searchByDepartmentIdOrdered(@Param("departmentId") Long departmentId);

    // interface based projection
    List<EmployeeNameEmailProjection> findProjectedByDepartmentId(Long departmentId);

    // class based projection via constructor expression
    @Query("SELECT new com.example.ems.projection.EmployeeSummaryDTO(e.name, e.department.name) FROM Employee e")
    List<EmployeeSummaryDTO> findEmployeeSummaries();

}
