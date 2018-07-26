package com.teamproject.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long employeeId) {
        super(String.format("Employee with id %d not foune", employeeId));

    }
}
