package com.example.paymentservice.controller;

import com.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/{orderId}")
    public String pay(@PathVariable String orderId) throws InterruptedException {
        return paymentService.processPayment(orderId);
    }
}
