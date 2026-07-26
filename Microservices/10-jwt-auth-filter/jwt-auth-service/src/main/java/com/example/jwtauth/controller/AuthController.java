package com.example.jwtauth.controller;

import com.example.jwtauth.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // demo login: issues a token for any username without checking a password
    @PostMapping("/login")
    public String login(@RequestParam String username) {
        return jwtTokenProvider.createToken(username);
    }
}
