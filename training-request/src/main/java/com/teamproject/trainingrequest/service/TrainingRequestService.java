package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import com.teamproject.trainingrequest.exception.TrainingRequestNotFoundException;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.Employee;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.repository.TrainingRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        TrainingRequestEntity entity = createEntity(createTrainingRequest);
        entity.setRequestedByFirstName(employee.getFirstName());
        entity.setRequestedByLastName(employee.getLastName());
        TrainingRequestEntity saved = repo.save(entity);

        return saved.getId();
    }

    private TrainingRequestEntity createEntity(CreateTrainingRequest createTrainingRequest) {
        TrainingRequestEntity entity = new TrainingRequestEntity();
        entity.setEmployeeId(createTrainingRequest.getEmployeeId());
        entity.setLocation(createTrainingRequest.getLocation());
        entity.setDescription(createTrainingRequest.getDescription());
        entity.setCost(createTrainingRequest.getCost());

        return entity;
    }

    @Override
    public List<TrainingRequest> getOpenTrainingRequests() {
        return repo.getOpenTrainingRequests().stream().map(t ->
                convertToTrainingRequest(t)).collect(Collectors.toList());

    }

    @Override
    public List<TrainingRequest> getTrainingRequestByCost(BigDecimal amount) {
        return repo.getTrainingRequestEntitiesByCostAfter(amount).stream().map(t ->
                convertToTrainingRequest(t)).collect(Collectors.toList());
    }

    @Override
    public void approveTrainingRequest(Long trainingRequestId, String approver) {
        TrainingRequestEntity e = repo.findById(trainingRequestId).orElseThrow(() -> new TrainingRequestNotFoundException(trainingRequestId));
        e.setApprovedBy(approver);
        e.setApprovedDate(new Date());
        repo.save(e);
    }

    private TrainingRequest convertToTrainingRequest(TrainingRequestEntity entity) {
        return modelMapper.map(entity,TrainingRequest.class);

    }

}
