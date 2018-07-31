package com.teamproject.trainingrequest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApproveTrainingRequest {

    @NotNull(message = "Approver is required")
    @ApiModelProperty(required = true, value = "User name of approver")
    private String approver;
}
