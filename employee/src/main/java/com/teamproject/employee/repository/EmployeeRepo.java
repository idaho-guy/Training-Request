package com.teamproject.employee.repository;

import com.teamproject.employee.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<EmployeeEntity, Long> {

}
