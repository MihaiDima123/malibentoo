package com.malibentoo.exception.api;

import jakarta.annotation.Nonnull;

import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_ALREADY_EXISTS;
import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_NOT_FOUND;
import static com.malibentoo.exception.api.ApiExceptionError.PROPERTY_REQUIRED;
import static com.malibentoo.exception.api.ApiExceptionError.replaceMessage;

public class ApiExceptionFactory {
    public static ApiException propertyAlreadyExists(@Nonnull String propertyName) {
        return ApiException.of(
                replaceMessage(PROPERTY_ALREADY_EXISTS, propertyName));
    }

    public static ApiException propertyNotFound(@Nonnull String propertyName) {
        return ApiException.of(
                replaceMessage(PROPERTY_NOT_FOUND, propertyName));
    }

    public static ApiException propertyRequired(@Nonnull String propertyName) {
        return ApiException.of(
                replaceMessage(PROPERTY_REQUIRED, propertyName));
    }
}
