package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        // these messages are written to both the console appender and the file appender
        logger.info("Application started");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
    }
}
