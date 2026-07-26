package com.example.downstreamservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownstreamController {

    // always fails, to demonstrate the gateway's circuit breaker + fallback
    @GetMapping("/downstream/unstable")
    public String unstable() {
        throw new RuntimeException("downstream-service is unavailable");
    }
}
