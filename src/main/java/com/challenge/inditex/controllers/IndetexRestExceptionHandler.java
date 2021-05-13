package com.challenge.inditex.controllers;

import com.challenge.inditex.responses.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IndetexRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> exceptionHandler(ResponseStatusException exception, WebRequest request) {
        if (exception.getCause() != null)
            return new ResponseEntity<>(new ApiError(exception.getStatus(), exception.getReason(), exception.getCause().getMessage()), exception.getStatus());
        return new ResponseEntity<>(new ApiError(exception.getStatus(), exception.getReason(), ""), exception.getStatus());
    }
}
