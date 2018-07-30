package com.teamproject.employee.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Employee")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;


}
