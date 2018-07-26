package com.teamproject.employee.model;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private String message;
    private Date date;

    public ExceptionResponse(String message) {
        this.message = message;
        this.date = new Date();
    }
}
