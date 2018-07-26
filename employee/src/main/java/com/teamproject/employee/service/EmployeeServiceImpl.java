package com.teamproject.employee.service;

import com.teamproject.employee.entity.EmployeeEntity;
import com.teamproject.employee.model.Employee;
import com.teamproject.employee.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        modelMapper = new ModelMapper();
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).map(employeeEntity ->
                convertToEmployee(employeeEntity)).orElse(null);
    }

    private Employee convertToEmployee(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, Employee.class);
    }
}
