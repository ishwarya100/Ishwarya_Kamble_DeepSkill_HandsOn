package com.example.loanservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoanController {

    // dummy response, no backend connectivity, as specified in the exercise
    @GetMapping("/loans/{number}")
    public Map<String, Object> getLoan(@PathVariable String number) {
        return Map.of("number", number, "type", "car", "loan", 400000, "emi", 3258, "tenure", 18);
    }
}
