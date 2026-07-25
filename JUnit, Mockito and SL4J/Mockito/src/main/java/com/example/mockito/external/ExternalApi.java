package com.example.mockito.external;

public interface ExternalApi {
    String getData();

    // takes an argument, used for the argument matcher exercise
    String getDataById(String id);
}
