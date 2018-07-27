package com.teamproject.trainingrequest.model;

import lombok.Data;

import java.util.Date;

@Data
public class TrainingRequest extends CreateTrainingRequest {

    private Long id;
    private String requestedByFirstName;
    private String requestedByLastName;
    private String approvedBy;
    private Date approvedDate;
}
