package com.teamproject.employee.controller;

import com.teamproject.employee.model.Employee;
import com.teamproject.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Long id) {
        return employeeService.getEmployeeById(id);
    }
}
