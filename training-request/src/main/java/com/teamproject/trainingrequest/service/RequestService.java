package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;

import java.util.List;

public interface RequestService {
    Long createTrainingRequest(CreateTrainingRequest createTrainingRequest);
    List<TrainingRequest> getOpenTrainingRequests();
}
