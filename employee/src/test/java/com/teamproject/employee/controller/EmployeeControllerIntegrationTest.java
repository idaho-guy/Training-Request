package com.teamproject.employee.controller;

import com.teamproject.employee.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/test-schema.sql","/test-data.sql"})
public class EmployeeControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getEmployeeByID() {
        Employee result = testRestTemplate.getForObject("/employees/1", Employee.class);
        assertEquals(new Long(1), result.getId());
        assertEquals("cbarbosa@gmail.com", result.getEmail());
        assertEquals("Ceasar", result.getFirstName());
        assertEquals("Barbosa", result.getLastName());
        assertEquals("Test", result.getOffice());
        assertEquals("http://ouch.com", result.getImageUrl());
        assertEquals("Technical Analyst", result.getTitle());
    }



}