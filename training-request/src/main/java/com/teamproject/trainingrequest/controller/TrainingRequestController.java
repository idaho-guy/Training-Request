package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TrainingRequestController {

    private RequestService requestService;

    public TrainingRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/trainingrequests")
    public ResponseEntity createTrainingRequest(@RequestBody CreateTrainingRequest createTrainingRequest) {
        Long trainingRequestId = requestService.createTrainingRequest(createTrainingRequest);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put(HttpHeaders.LOCATION, Collections.singletonList("/trainingrequests/" + trainingRequestId));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/trainingrequests")
    public List<TrainingRequest> getOpenTrainingRequests() {
        List<TrainingRequest> openTrainingRequests = requestService.getOpenTrainingRequests();
        return openTrainingRequests;
    }
}
