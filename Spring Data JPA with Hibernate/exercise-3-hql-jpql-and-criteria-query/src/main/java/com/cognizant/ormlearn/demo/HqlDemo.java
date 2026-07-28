package com.cognizant.ormlearn.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;

@Component
public class HqlDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(HqlDemo.class);

    @Autowired
    private EmployeeService employeeService;

    public void runDemo() {
        testGetAllPermanentEmployees();
        testAverageSalary();
        testAverageSalaryByDepartment();
        testGetAllEmployeesNative();
    }

    // all permanent employees with department and skills fetched
    private void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    // average salary across all employees
    private void testAverageSalary() {
        LOGGER.info("Start");
        double averageSalary = employeeService.getAverageSalary();
        LOGGER.debug("Average salary:{}", averageSalary);
        LOGGER.info("End");
    }

    // average salary filtered by department id
    private void testAverageSalaryByDepartment() {
        LOGGER.info("Start");
        double averageSalary = employeeService.getAverageSalary(1);
        LOGGER.debug("Average salary for department 1:{}", averageSalary);
        LOGGER.info("End");
    }

    // get all employees using a native query
    private void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All employees (native query):{}", employees);
        LOGGER.info("End");
    }
}
