package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.ApproveTrainingRequest;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
public class TrainingRequestController {

    protected static final String TRAINING_REQUEST_OPEN = "open";
    private static final String MINIMUM_TRAINING_AMOUNT = "0.00";

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
    public List<TrainingRequest> getTrainingRequests(@RequestParam(value = "cost", required = false) BigDecimal cost) {
        if(cost != null){
            return requestService.getTrainingRequestByCost(cost);
        }
        return requestService.getOpenTrainingRequests();

    }

    @PutMapping("/trainingrequests/{id}")
    public ResponseEntity approveTrainingRequest(@PathVariable(name = "id") Long id, @RequestBody ApproveTrainingRequest approveTrainingRequest) {
        requestService.approveTrainingRequest(id, approveTrainingRequest.getApprover());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
