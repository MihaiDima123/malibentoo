package com.malibentoo.malibentoo.unit.exception;

import com.malibentoo.exception.api.ApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ApiExceptionTest {
    private static final String TEST_ERROR_MESSAGE = "Test";

    @Test
    public void testStaticMethodReturnsAnApiExceptionWithTheCorrectMessage() {
        assertEquals(ApiException.of(TEST_ERROR_MESSAGE).getMessage(), TEST_ERROR_MESSAGE);
    }

    @Test
    public void testStaticMethodMessageIsNullable() {
        assertNull(ApiException.of(null).getMessage());
    }
}
