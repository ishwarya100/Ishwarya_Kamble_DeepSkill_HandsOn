package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "john_doe";
        int loginCount = 3;

        // placeholders avoid string concatenation and are only evaluated if the level is enabled
        logger.info("User {} logged in", username);
        logger.info("User {} has logged in {} times today", username, loginCount);
        logger.warn("User {} failed login attempt number {}", username, loginCount);
    }
}
