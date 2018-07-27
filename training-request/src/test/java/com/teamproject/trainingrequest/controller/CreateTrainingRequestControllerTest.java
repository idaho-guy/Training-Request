package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateTrainingRequestControllerTest {

    @InjectMocks
    TrainingRequestController trainingRequestController;

    @Mock
    RequestService service;

    @Test
    public void createTrainingRequest() {
        CreateTrainingRequest createTrainingRequest = new CreateTrainingRequest();
        when(service.createTrainingRequest(createTrainingRequest)).thenReturn(new Long(1234));
        ResponseEntity responseEntity = trainingRequestController.createTrainingRequest(createTrainingRequest);
        assertNotNull(responseEntity);

        String location = responseEntity.getHeaders().get("location").get(0);
        assertEquals("/trainingrequests/1234", location);
    }
    
    @Test
    public void getOpenTrainingRequests() {
        List expectedList = new ArrayList();
        when(service.getOpenTrainingRequests()).thenReturn(expectedList);
        List<TrainingRequest> trainingRequests = trainingRequestController.getTrainingRequests(null);
        assertNotNull(trainingRequests);
        assertSame(expectedList, trainingRequests);
    }

    @Test
    public void getOpenTrainingRequestAboveCost() {
        List expectedList = new ArrayList();
        BigDecimal amount = new BigDecimal(5.00);
        when(service.getTrainingRequestByCost(amount)).thenReturn(expectedList);
        List<TrainingRequest> trainingRequests = trainingRequestController.getTrainingRequests(amount);
        assertNotNull(trainingRequests);
        assertSame(expectedList, trainingRequests);
    }


}