package com.malibentoo.exception.api;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ApiExceptionError {
    PROPERTY_ALREADY_EXISTS("{{}} already exists", "{{}}"),
    PROPERTY_NOT_FOUND("{{}} not found", "{{}}"),
    PROPERTY_REQUIRED("{{}} is required", "{{}}"),;
    private final String message;
    private final String template;

    @Nonnull
    public static String replaceMessage(@Nonnull ApiExceptionError error,
                                        @Nonnull String message) {
        Objects.requireNonNull(error, message);
        return error.getMessage().replace(error.getTemplate(), message);
    }
}
