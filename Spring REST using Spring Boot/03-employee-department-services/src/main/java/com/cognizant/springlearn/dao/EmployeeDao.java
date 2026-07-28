package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Employee;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        @SuppressWarnings("unchecked")
        ArrayList<Employee> employees = context.getBean("employeeList", ArrayList.class);
        EMPLOYEE_LIST = employees;

        LOGGER.info("END");
    }

    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return EMPLOYEE_LIST;
    }

}
