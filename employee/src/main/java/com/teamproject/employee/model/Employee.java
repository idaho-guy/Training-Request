package com.teamproject.employee.model;

import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;


}
