package com.malibentoo.malibentoo.unit.utils;

import com.malibentoo.utils.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringUtilsTest {
    @Test
    public void testIsNullOrEmpty_givenValidString_shouldReturnTrue() {
        assertFalse(StringUtils.isNullOrEmpty("test"));
    }

    @Test
    public void testIsNullOrEmpty_givenNullString_shouldReturnFalse() {
        assertTrue(StringUtils.isNullOrEmpty(null));
    }

    @Test
    public void testIsNullOrEmpty_givenEmptyString_shouldReturnFalse() {
        assertTrue(StringUtils.isNullOrEmpty(""));

    }

    @Test
    public void testIsNullOrEmpty_givenEmptyWithTrim_shouldReturnFalse() {
        assertTrue(StringUtils.isNullOrEmpty(" "));
        assertTrue(StringUtils.isNullOrEmpty("  "));
        assertTrue(StringUtils.isNullOrEmpty("   "));
    }
}
