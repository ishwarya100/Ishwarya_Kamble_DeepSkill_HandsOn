package com.example.mockito.basic;

import com.example.mockito.file.FileWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ExceptionTest {

    @Test
    public void testVoidMethodThrowsException() {
        FileWriter mockWriter = mock(FileWriter.class);

        // stub the void method to throw an exception when called
        doThrow(new RuntimeException("Write failed")).when(mockWriter).write("bad content");

        assertThrows(RuntimeException.class, () -> mockWriter.write("bad content"));
    }
}
