package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url="${employee.service.url}")
public interface EmployeeService {

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable(name = "id") Long id);

    @GetMapping("/employees-string/{id}")
    String getEmployeeByIdSTring(@PathVariable(name = "id") Long id);

}
