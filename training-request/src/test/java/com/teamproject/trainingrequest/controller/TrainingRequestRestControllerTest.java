package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.ApproveTrainingRequest;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingRequestRestControllerTest {

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
        expectedList.add(new TrainingRequest());
        when(service.getOpenTrainingRequests()).thenReturn(expectedList);
        ResponseEntity<List<TrainingRequest>> requestResponse = trainingRequestController.getTrainingRequests(null);
        assertSame(expectedList, requestResponse.getBody());
        assertSame(HttpStatus.OK, requestResponse.getStatusCode());
    }

    @Test
    public void getOpenTrainingRequestsNoneFound() {
        List expectedList = new ArrayList();
        when(service.getOpenTrainingRequests()).thenReturn(expectedList);
        ResponseEntity<List<TrainingRequest>> trainingRequests = trainingRequestController.getTrainingRequests(null);
        assertSame(HttpStatus.NOT_FOUND, trainingRequests.getStatusCode());
    }

    @Test
    public void getOpenTrainingRequestAboveCost() {
        List expectedList = new ArrayList();
        expectedList.add(new TrainingRequest());
        BigDecimal amount = new BigDecimal(5.00);
        when(service.getTrainingRequestByCost(amount)).thenReturn(expectedList);
        ResponseEntity<List<TrainingRequest>> requestResponse = trainingRequestController.getTrainingRequests(amount);

        assertSame(HttpStatus.OK, requestResponse.getStatusCode());
        assertSame(expectedList, requestResponse.getBody());
    }

    @Test
    public void getOpenTrainingRequestAboveCostNoneFound() {
        when(service.getTrainingRequestByCost(new BigDecimal(5.00))).thenReturn(new ArrayList());
        ResponseEntity<List<TrainingRequest>> requestResponse = trainingRequestController.getTrainingRequests(new BigDecimal(5.00));

        assertSame(HttpStatus.NOT_FOUND, requestResponse.getStatusCode());
    }
    
    @Test
    public void approveTrainingRequest() {
        ApproveTrainingRequest approveTrainingRequest = new ApproveTrainingRequest();
        String approver = "x";
        Long trainingRequestId = 123L;
        approveTrainingRequest.setApprover(approver);
        ResponseEntity responseEntity = trainingRequestController.approveTrainingRequest(trainingRequestId, approveTrainingRequest);
        verify(service, times(1)).approveTrainingRequest(trainingRequestId, approver);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

    }


}