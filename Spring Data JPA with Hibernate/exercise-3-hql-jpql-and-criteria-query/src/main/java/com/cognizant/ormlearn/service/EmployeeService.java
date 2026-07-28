package com.cognizant.ormlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeRepository.getAllPermanentEmployees();
        LOGGER.info("End");
        return employees;
    }

    @Transactional
    public double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    @Transactional
    public double getAverageSalary(int departmentId) {
        return employeeRepository.getAverageSalary(departmentId);
    }

    @Transactional
    public List<Employee> getAllEmployeesNative() {
        LOGGER.info("Start");
        return employeeRepository.getAllEmployeesNative();
    }
}
