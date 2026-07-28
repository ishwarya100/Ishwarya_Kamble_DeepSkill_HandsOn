package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private static final String SIGNING_KEY = "secretkey";

    // token expiry set to 20 minutes from issue time
    private static final long TOKEN_VALIDITY_MILLIS = 1200000;

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START");
        LOGGER.debug(authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("END");
        return map;
    }

    private String getUser(String authHeader) {
        LOGGER.info("START");

        String encodedCredentials = authHeader.replace("Basic ", "");
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials));
        String user = credentials.substring(0, credentials.indexOf(":"));

        LOGGER.debug(user);
        LOGGER.info("END");
        return user;
    }

    private String generateJwt(String user) {
        LOGGER.info("START");

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        // set the token issue time as current time
        builder.setIssuedAt(new Date());
        // set the token expiry as 20 minutes from now
        builder.setExpiration(new Date(new Date().getTime() + TOKEN_VALIDITY_MILLIS));
        builder.signWith(SignatureAlgorithm.HS256, SIGNING_KEY);
        String token = builder.compact();

        LOGGER.info("END");
        return token;
    }

}
