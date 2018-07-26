package com.teamproject.employee.service;

import com.teamproject.employee.entity.EmployeeEntity;
import com.teamproject.employee.model.Employee;
import com.teamproject.employee.repository.EmployeeRepo;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    @Test
    public void getEmployeeById() {
        EmployeeRepo employeeRepo = mock(EmployeeRepo.class);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        when(employeeRepo.findById(new Long(1))).thenReturn(Optional.of(employeeEntity));
        String email = "me@gmail.com";
        employeeEntity.setEmail(email);
        EmployeeServiceImpl service = new EmployeeServiceImpl(employeeRepo);
        Employee employee = service.getEmployeeById(new Long(1));
        assertEquals(email, employee.getEmail());
    }
}