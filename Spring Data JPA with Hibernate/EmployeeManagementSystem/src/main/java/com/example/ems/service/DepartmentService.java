package com.example.ems.service;

import com.example.ems.entity.Department;
import com.example.ems.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));
    }

    public Department update(Long id, Department updated) {
        Department existing = findById(id);
        existing.setName(updated.getName());
        return departmentRepository.save(existing);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> search(String keyword) {
        return departmentRepository.findByNameContainingIgnoreCase(keyword);
    }

}
