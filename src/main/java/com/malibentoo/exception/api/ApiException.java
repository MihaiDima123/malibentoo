package com.malibentoo.exception.api;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }

    public static ApiException of(String message) {
        return new ApiException(message);
    }
}
