package com.teamproject.employee.controller;

import com.teamproject.employee.exception.EmployeeNotFoundException;
import com.teamproject.employee.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNotFound(EmployeeNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
