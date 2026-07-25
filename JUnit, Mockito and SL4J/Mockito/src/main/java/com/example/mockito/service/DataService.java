package com.example.mockito.service;

import com.example.mockito.repository.Repository;

// generic service used only for the basic mocking exercises
public class DataService {

    private final Repository repository;

    public DataService(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return "Processed " + repository.getData();
    }
}
