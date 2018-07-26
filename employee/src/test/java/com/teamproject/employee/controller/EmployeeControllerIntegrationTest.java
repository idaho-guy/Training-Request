package com.teamproject.employee.controller;

import com.teamproject.employee.model.Employee;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @Ignore
    public void getEmployeeByID() {
        Employee result = testRestTemplate.getForObject("/employee/1", Employee.class);
        assertEquals(new Long(1), result.getId());
    }


}