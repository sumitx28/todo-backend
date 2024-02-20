package com.todospringboot.todobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotfoudExceptionHandler(ResourceNotFoundException ex , WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage() , request.getDescription(false), "TODO_NOT_FOUND");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Error> resourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException ex , WebRequest request) {
        Error error = new Error(LocalDateTime.now(), ex.getMessage() , request.getDescription(false), "TODO_ALREADY_EXISTS");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
