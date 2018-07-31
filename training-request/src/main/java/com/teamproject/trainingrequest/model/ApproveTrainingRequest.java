package com.teamproject.trainingrequest.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApproveTrainingRequest {

    @NotNull(message = "Approver is required")
    private String approver;
}
