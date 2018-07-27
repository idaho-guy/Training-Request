package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;

import java.math.BigDecimal;
import java.util.List;

public interface RequestService {
    Long createTrainingRequest(CreateTrainingRequest createTrainingRequest);
    List<TrainingRequest> getOpenTrainingRequests();

    List<TrainingRequest> getTrainingRequestByCost(BigDecimal amount);
}
