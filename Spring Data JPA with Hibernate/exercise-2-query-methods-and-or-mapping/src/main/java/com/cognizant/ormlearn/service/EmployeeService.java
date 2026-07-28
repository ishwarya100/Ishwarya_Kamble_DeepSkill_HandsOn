package com.cognizant.ormlearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import com.cognizant.ormlearn.repository.SkillRepository;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start");
        return employeeRepository.findById(id).get();
    }

    // Loads the employee together with department and skillList in one query
    @Transactional
    public Employee getWithDetails(int id) {
        LOGGER.info("Start");
        return employeeRepository.findByIdWithDetails(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start");
        employeeRepository.save(employee);
        LOGGER.info("End");
    }

    // Adds a skill to an employee's skill list within a single transaction
    // so the lazily loaded collection stays attached while it is modified
    @Transactional
    public void addSkillToEmployee(int employeeId, int skillId) {
        LOGGER.info("Start");
        Employee employee = employeeRepository.findByIdWithDetails(employeeId).get();
        Skill skill = skillRepository.findById(skillId).get();
        employee.getSkillList().add(skill);
        employeeRepository.save(employee);
        LOGGER.info("End");
    }
}
