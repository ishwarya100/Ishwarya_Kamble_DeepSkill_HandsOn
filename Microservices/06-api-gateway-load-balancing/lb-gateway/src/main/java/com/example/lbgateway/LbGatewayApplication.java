package com.example.lbgateway;

import com.example.lbgateway.config.LoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication
@LoadBalancerClient(name = "example-service", configuration = LoadBalancerConfiguration.class)
public class LbGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LbGatewayApplication.class, args);
    }
}
