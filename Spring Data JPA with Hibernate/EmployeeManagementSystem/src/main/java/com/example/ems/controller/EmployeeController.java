package com.example.ems.controller;

import com.example.ems.entity.Employee;
import com.example.ems.projection.EmployeeNameEmailProjection;
import com.example.ems.projection.EmployeeSummaryDTO;
import com.example.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    // paginated and sorted employee listing
    @GetMapping
    public ResponseEntity<Page<Employee>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> byDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.findByDepartment(departmentId));
    }

    @GetMapping("/department-name/{departmentName}")
    public ResponseEntity<List<Employee>> byDepartmentName(@PathVariable String departmentName) {
        return ResponseEntity.ok(employeeService.findByDepartmentName(departmentName));
    }

    @GetMapping("/projections/department/{departmentId}")
    public ResponseEntity<List<EmployeeNameEmailProjection>> projectionsByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getProjectionsByDepartment(departmentId));
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<EmployeeSummaryDTO>> summaries() {
        return ResponseEntity.ok(employeeService.getEmployeeSummaries());
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> batchInsert(@RequestBody List<Employee> employees) {
        employeeService.batchInsert(employees);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
