package com.teamproject.employee.controller;

import com.teamproject.employee.model.Employee;
import com.teamproject.employee.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Test
    public void getEmployeeById_Ceasar() {
        String expectedFirstName = "Ceasar";
        assertEmployee(expectedFirstName);
    }

    @Test
    public void getEmployeeById_Julie() {
        String expectedFirstName = "Julie";
        assertEmployee(expectedFirstName);
    }

    private void assertEmployee(String expectedFirstName) {
        Employee expectedEmployee = new Employee();
        expectedEmployee.setFirstName(expectedFirstName);
        when(employeeService.getEmployeeById(new Long(1))).thenReturn(expectedEmployee);
        Employee employee = employeeController.getEmployeeById(1L);
        assertEquals(expectedFirstName, employee.getFirstName());
    }

}