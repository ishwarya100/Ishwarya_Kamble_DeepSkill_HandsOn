package com.example.exampleservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Value("${server.port}")
    private String port;

    // returns its own port so we can see the load balancer alternate between instances
    @GetMapping("/loadbalanced/hello")
    public String hello() {
        return "Hello from example-service instance on port " + port;
    }
}
