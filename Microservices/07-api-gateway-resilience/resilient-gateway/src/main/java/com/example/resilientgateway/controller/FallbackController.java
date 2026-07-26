package com.example.resilientgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    // served when downstream-service's circuit is open or the call fails
    @GetMapping("/fallback")
    public String fallback() {
        return "downstream-service is currently unavailable, please try again later";
    }
}
