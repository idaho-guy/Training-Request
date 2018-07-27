package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import com.teamproject.trainingrequest.model.Employee;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.repository.TrainingRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingRequestService implements RequestService {

    private final ModelMapper modelMapper;
    private TrainingRequestRepository repo;
    private EmployeeService employeeService;

    public TrainingRequestService(TrainingRequestRepository repo, EmployeeService employeeService) {
        this.repo = repo;
        this.employeeService = employeeService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Long createTrainingRequest(CreateTrainingRequest createTrainingRequest) {
        Employee employee = employeeService.getEmployeeById(createTrainingRequest.getEmployeeId());
        TrainingRequestEntity entity = modelMapper.map(createTrainingRequest, TrainingRequestEntity.class);
        entity.setRequestedByFirstName(employee.getFirstName());
        entity.setRequestedByLastName(employee.getLastName());
        TrainingRequestEntity saved = repo.save(entity);

        return saved.getId();
    }

    @Override
    public List<TrainingRequest> getOpenTrainingRequests() {
        return repo.getOpenTrainingRequests().stream().map(t -> convertToTrainingRequest(t)).collect(Collectors.toList());

    }

    private TrainingRequest convertToTrainingRequest(TrainingRequestEntity entity) {
        return modelMapper.map(entity,TrainingRequest.class);

    }

}
