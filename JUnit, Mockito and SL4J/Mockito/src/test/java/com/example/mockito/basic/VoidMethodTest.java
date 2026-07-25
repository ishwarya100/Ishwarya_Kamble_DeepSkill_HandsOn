package com.example.mockito.basic;

import com.example.mockito.file.FileWriter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VoidMethodTest {

    @Test
    public void testVoidMethod() {
        FileWriter mockWriter = mock(FileWriter.class);

        // stub the void method to do nothing when called
        doNothing().when(mockWriter).write("Sample Content");

        mockWriter.write("Sample Content");

        verify(mockWriter).write("Sample Content");
    }
}
