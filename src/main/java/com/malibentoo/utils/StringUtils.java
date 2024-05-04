package com.malibentoo.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || org.springframework.util.StringUtils.trimAllWhitespace(str).isEmpty();
    }
}
