package com.malibentoo.malibentoo.unit.exception;

import com.malibentoo.exception.api.ApiExceptionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.malibentoo.exception.api.ApiExceptionError.replaceMessage;
import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_REQUIRED;
import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_NOT_FOUND;
import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_ALREADY_EXISTS;

public class ApiExceptionFactoryTest {
    private final static String TEST_PROPERTY_NAME = "testPropertyName";

    @Test
    public void testApiExceptionFactoryPropertyAlreadyExists() {
        assertEquals(
                ApiExceptionFactory.propertyAlreadyExists(TEST_PROPERTY_NAME).getMessage(),
                replaceMessage(PROPERTY_ALREADY_EXISTS, TEST_PROPERTY_NAME)
        );
    }

    @Test
    public void testApiExceptionFactoryPropertyNotFound() {
        assertEquals(
                ApiExceptionFactory.propertyNotFound(TEST_PROPERTY_NAME).getMessage(),
                replaceMessage(PROPERTY_NOT_FOUND, TEST_PROPERTY_NAME)
        );
    }

    @Test
    public void testApiExceptionFactoryPropertyRequired() {
        assertEquals(
                ApiExceptionFactory.propertyRequired(TEST_PROPERTY_NAME).getMessage(),
                replaceMessage(PROPERTY_REQUIRED, TEST_PROPERTY_NAME)
        );
    }
}
