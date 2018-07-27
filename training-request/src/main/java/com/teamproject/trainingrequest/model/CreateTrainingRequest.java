package com.teamproject.trainingrequest.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTrainingRequest {

    private String location;
    private String description;
    private BigDecimal cost;
    private Long employeeId;
}
