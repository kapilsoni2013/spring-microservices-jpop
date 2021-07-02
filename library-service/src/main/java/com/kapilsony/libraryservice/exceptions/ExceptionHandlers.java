package com.kapilsony.libraryservice.exceptions;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Value("${app.version}")
    private String appVersion;

    private CustomErrorResponse getErrorInstance(){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setVersion(appVersion);
        return errors;
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleBookNotFound(BookNotFoundException ex, WebRequest request) {
        CustomErrorResponse errors =getErrorInstance();
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        CustomErrorResponse errors = getErrorInstance();
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException ex, HttpServletResponse response) {
        String responseStr = StandardCharsets.UTF_8.decode(ex.responseBody().get()).toString();
        return ResponseEntity
                .status(ex.status())
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseStr);
    }
}
