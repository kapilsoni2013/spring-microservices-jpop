package com.kapilsony.userservice.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Value("${app.version}")
    private String appVersion;

    private CustomErrorResponse getErrorInstance(){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setVersion(appVersion);
        return errors;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        CustomErrorResponse errors = getErrorInstance();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        CustomErrorResponse errors = getErrorInstance();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }


}
