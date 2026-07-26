package com.example.paymentservice.service;

import org.springframework.stereotype.Service;

// stands in for the slow/unreliable third-party payment API mentioned in the exercise
@Service
public class ThirdPartyPaymentClient {

    public String charge(String orderId) throws InterruptedException {
        // simulate a slow downstream call that regularly times out
        Thread.sleep(3000);
        throw new RuntimeException("Third-party payment API timed out for order " + orderId);
    }
}
