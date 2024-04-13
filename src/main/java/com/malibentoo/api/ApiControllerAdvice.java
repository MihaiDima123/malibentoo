package com.malibentoo.api;

import com.malibentoo.exception.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerAdvice.class);
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "An error occurred while processing the request";

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleApiException(ApiException apiException) {
        LOGGER.warn(apiException.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder(
                        apiException,
                        ProblemDetail.forStatusAndDetail(
                                HttpStatus.BAD_REQUEST,
                                apiException.getMessage()
                        )
                ).build());
    }

    @ExceptionHandler({Throwable.class, Exception.class, Error.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> onAnyException(ApiException apiException) {
        LOGGER.error(INTERNAL_SERVER_ERROR_MESSAGE, apiException);

        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.builder(
                        apiException,
                        ProblemDetail.forStatusAndDetail(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                INTERNAL_SERVER_ERROR_MESSAGE
                        )
                ).build());
    }
}
