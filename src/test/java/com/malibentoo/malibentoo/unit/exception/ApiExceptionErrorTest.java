package com.malibentoo.malibentoo.unit.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_ALREADY_EXISTS;
import static com.malibentoo.exception.api.ApiExceptionError.replaceMessage;

public class ApiExceptionErrorTest {
    private static final String TEST_MESSAGE = "test message";

    @Test
    public void testReplaceMessageReturnsTheRightMessage() {
        var actual = replaceMessage(PROPERTY_ALREADY_EXISTS, TEST_MESSAGE);
        var expected = PROPERTY_ALREADY_EXISTS
                .getMessage()
                .replace(PROPERTY_ALREADY_EXISTS.getTemplate(), TEST_MESSAGE);

        assertEquals(expected, actual);
    }

    @Test
    public void testReplaceMessageRequiresNonNullApiExceptionErrorEnum() {
        assertThrows(NullPointerException.class, () -> replaceMessage(null, TEST_MESSAGE));
    }

    @Test
    public void testReplaceMessageRequiresNonNullMessage() {
        assertThrows(NullPointerException.class, () -> replaceMessage(PROPERTY_ALREADY_EXISTS, null));
    }
}
