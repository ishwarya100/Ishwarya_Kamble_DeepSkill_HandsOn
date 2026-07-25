package com.example.mockito.advanced;

import com.example.mockito.repository.Repository;
import com.example.mockito.service.DataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseMockTest {

    @Test
    public void testServiceWithMockRepository() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        DataService dataService = new DataService(mockRepository);
        String result = dataService.processData();

        assertEquals("Processed Mock Data", result);
    }
}
