package com.example.mockito.basic;

import com.example.mockito.external.ExternalApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultipleReturnTest {

    @Test
    public void testMultipleReturnValues() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");

        String firstResult = mockApi.getData();
        String secondResult = mockApi.getData();

        assertEquals("First Mock Data", firstResult);
        assertEquals("Second Mock Data", secondResult);
    }
}
