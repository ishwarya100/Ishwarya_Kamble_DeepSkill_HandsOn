package com.cognizant.ormlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.demo.CountryDemo;

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

        @Override
        public void run(String... args) throws Exception {
            LOGGER.info("Running Country demo (Hands on 1: Spring Data JPA quick example)");
            countryDemo.runDemo();
        }
    }
}
