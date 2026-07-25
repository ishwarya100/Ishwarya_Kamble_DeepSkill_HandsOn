package com.example.mockito.basic;

import com.example.mockito.external.ExternalApi;
import com.example.mockito.external.NetworkClient;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class OrderVerificationTest {

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        NetworkClient mockNetwork = mock(NetworkClient.class);

        mockNetwork.connect();
        mockApi.getData();

        // verify the methods were called in the expected order
        InOrder inOrder = inOrder(mockNetwork, mockApi);
        inOrder.verify(mockNetwork).connect();
        inOrder.verify(mockApi).getData();
    }
}
