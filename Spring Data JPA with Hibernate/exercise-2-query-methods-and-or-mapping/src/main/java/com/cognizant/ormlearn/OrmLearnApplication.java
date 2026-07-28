package com.cognizant.ormlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.demo.CountryDemo;
import com.cognizant.ormlearn.demo.EmployeeDemo;
import com.cognizant.ormlearn.demo.StockDemo;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");
    }

    // Runs every hands-on demo, in order, once the application context is ready
    @Component
    static class DemoRunner implements CommandLineRunner {

        @Autowired
        private CountryDemo countryDemo;

        @Autowired
        private EmployeeDemo employeeDemo;

        @Autowired
        private StockDemo stockDemo;

        @Override
        public void run(String... args) throws Exception {
            LOGGER.info("Running Country query methods demo (Hands on 1)");
            countryDemo.runDemo();

            LOGGER.info("Running Stock query methods demo (Hands on 2)");
            stockDemo.runDemo();

            LOGGER.info("Running Employee/Department/Skill O/R mapping demo (Hands on 3 to 6)");
            employeeDemo.runDemo();
        }
    }
}
