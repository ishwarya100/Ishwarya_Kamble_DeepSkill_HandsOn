package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

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

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START");

        // find the position of the matching employee and replace it in the list
        int index = -1;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new EmployeeNotFoundException();
        }

        EMPLOYEE_LIST.set(index, employee);

        LOGGER.info("END");
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        LOGGER.info("START");

        boolean removed = EMPLOYEE_LIST.removeIf(employee -> employee.getId().equals(id));

        if (!removed) {
            throw new EmployeeNotFoundException();
        }

        LOGGER.info("END");
    }

}
