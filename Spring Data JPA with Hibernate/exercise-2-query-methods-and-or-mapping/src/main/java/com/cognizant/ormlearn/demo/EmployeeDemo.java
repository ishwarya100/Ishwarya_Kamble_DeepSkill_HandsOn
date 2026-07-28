package com.cognizant.ormlearn.demo;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;

@Component
public class EmployeeDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDemo.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public void runDemo() {
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();
        testGetDepartment();
        testAddSkillToEmployee();
    }

    // get employee along with department and skills
    private void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.getWithDetails(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    // add employee, assigning an existing department
    private void testAddEmployee() {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("Ava Robinson");
        employee.setSalary(52000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(LocalDate.of(1993, 6, 15));
        Department department = departmentService.get(1);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Added employee:{}", employee);
        LOGGER.info("End");
    }

    // update employee, assigning a different department
    private void testUpdateEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Department department = departmentService.get(2);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Updated employee:{}", employee);
        LOGGER.info("End");
    }

    // get department along with its employee list
    private void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.getWithEmployees(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees:{}", department.getEmployeeList());
        LOGGER.info("End");
    }

    // add a skill to an employee that does not already have it
    private void testAddSkillToEmployee() {
        LOGGER.info("Start");
        employeeService.addSkillToEmployee(3, 2);
        LOGGER.info("End");
    }
}
