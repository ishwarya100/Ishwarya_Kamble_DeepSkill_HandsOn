package com.example.paymentservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final ThirdPartyPaymentClient thirdPartyPaymentClient;

    public PaymentService(ThirdPartyPaymentClient thirdPartyPaymentClient) {
        this.thirdPartyPaymentClient = thirdPartyPaymentClient;
    }

    @CircuitBreaker(name = "thirdPartyApi", fallbackMethod = "paymentFallback")
    public String processPayment(String orderId) throws InterruptedException {
        return thirdPartyPaymentClient.charge(orderId);
    }

    // fallback runs when the circuit is open or the call fails/times out
    private String paymentFallback(String orderId, Throwable throwable) {
        logger.warn("Fallback triggered for order {}: {}", orderId, throwable.getMessage());
        return "Payment for order " + orderId + " queued for retry (fallback response)";
    }
}
