package com.malibentoo.exception.api;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public class ApiExceptionFactory {
    public static ApiException propertyAlreadyExists(@Nonnull String propertyName) {
        Objects.requireNonNull(propertyName);
        return new ApiException(propertyName + " already exists");
    }

    public static ApiException propertyNotFound(@Nonnull String propertyName) {
        Objects.requireNonNull(propertyName);
        return new ApiException(propertyName + " not found");
    }

    public static ApiException propertyRequired(@Nonnull String propertyName) {
        Objects.requireNonNull(propertyName);
        return new ApiException(propertyName + " required");
    }
}
