package com.example.mockito.basic;

import com.example.mockito.external.ExternalApi;
import com.example.mockito.service.MyService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VerifyTest {

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        // confirm getData was called exactly once
        verify(mockApi).getData();
    }
}
