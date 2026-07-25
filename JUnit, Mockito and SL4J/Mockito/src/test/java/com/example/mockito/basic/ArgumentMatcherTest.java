package com.example.mockito.basic;

import com.example.mockito.external.ExternalApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArgumentMatcherTest {

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getDataById(anyString())).thenReturn("Matched Data");

        String result = mockApi.getDataById("user123");

        // verify the method was called with any string argument
        verify(mockApi).getDataById(anyString());
        assertEquals("Matched Data", result);
    }
}
