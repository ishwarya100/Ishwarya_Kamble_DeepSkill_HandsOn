package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.projection.EmployeeNameEmailProjection;
import com.example.ems.projection.EmployeeSummaryDTO;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final int BATCH_SIZE = 20;

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EntityManager entityManager;

    public Employee create(Employee employee) {
        resolveDepartment(employee);
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        if (updated.getDepartment() != null) {
            existing.setDepartment(updated.getDepartment());
            resolveDepartment(existing);
        }
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> findByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public List<EmployeeNameEmailProjection> getProjectionsByDepartment(Long departmentId) {
        return employeeRepository.findProjectedByDepartmentId(departmentId);
    }

    public List<EmployeeSummaryDTO> getEmployeeSummaries() {
        return employeeRepository.findEmployeeSummaries();
    }

    // bulk insert using the hibernate flush and clear pattern for batching
    @Transactional
    public void batchInsert(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            resolveDepartment(employee);
            entityManager.persist(employee);
            if ((i + 1) % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    // attaches a managed department instance when only its id was supplied
    private void resolveDepartment(Employee employee) {
        Department department = employee.getDepartment();
        if (department != null && department.getId() != null) {
            Department managed = departmentRepository.findById(department.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + department.getId()));
            employee.setDepartment(managed);
        }
    }

}
