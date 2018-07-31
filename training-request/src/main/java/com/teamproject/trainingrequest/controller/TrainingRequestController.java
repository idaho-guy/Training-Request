package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.ApproveTrainingRequest;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class TrainingRequestController {

    private RequestService requestService;

    public TrainingRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/trainingrequests/{id}")
    @ApiOperation(value = "Get Training Request by id")
    public TrainingRequest getTrainingRequest(@PathVariable(name = "id") Long id) {
        return requestService.getTrainingRequest(id);
    }

    @PostMapping("/trainingrequests")
    @ApiOperation(value = "Create Training Request", notes = "Endpoint used by employees to request training," +
            " newly created resource will be found in location header")
    public ResponseEntity<Void> createTrainingRequest(@Valid @RequestBody CreateTrainingRequest createTrainingRequest) {
        Long trainingRequestId = requestService.createTrainingRequest(createTrainingRequest);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put(HttpHeaders.LOCATION, Collections.singletonList("/trainingrequests/" + trainingRequestId));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/trainingrequests")
    @ApiOperation(value = "Get Training Requests", notes = "If no query paramenters provided," +
            " then all unapproved training requests will be returned;" +
            " If 'cost' parameter is passed in, all training requests above amount regardless of approval status will be returned.")
    public ResponseEntity<List<TrainingRequest>> getTrainingRequests(@RequestParam(value = "cost", required = false) BigDecimal cost) {

        if(cost != null){
            return getTrainingRequests(() -> requestService.getTrainingRequestByCost(cost));
        }else{
            return getTrainingRequests(() -> requestService.getOpenTrainingRequests());
        }


    }

    private ResponseEntity<List<TrainingRequest>> getTrainingRequests(Supplier<List<TrainingRequest>> supplier){
        List<TrainingRequest> requests = supplier.get();
        if(requests == null || requests.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requests,HttpStatus.OK);
    }

    @PutMapping("/trainingrequests/{id}")
    @ApiOperation(value = "Approve Training Request", notes = "Endpoint used by staff that approve training")
    public ResponseEntity<Void> approveTrainingRequest(@PathVariable(name = "id") Long id, @Valid @RequestBody ApproveTrainingRequest approveTrainingRequest) {
        requestService.approveTrainingRequest(id, approveTrainingRequest.getApprover());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
