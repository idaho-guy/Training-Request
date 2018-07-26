package com.teamproject.trainingrequest.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TrainingRequest {

    private String location;
    private String description;
    private BigDecimal cost;
    private Long employeeId;
}
