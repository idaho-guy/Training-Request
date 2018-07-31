package com.teamproject.trainingrequest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TrainingRequest extends CreateTrainingRequest {

    @ApiModelProperty(value = "Unique Id of the training requested (Required for approval process")
    private Long id;
    @ApiModelProperty(value = "First name of user requesting training", position = 20)
    private String requestedByFirstName;
    @ApiModelProperty(value = "Last name of user requesting training", position = 30)
    private String requestedByLastName;
    @ApiModelProperty(value = "User name of approver", position = 40)
    private String approvedBy;
    @ApiModelProperty(value = "Date training was approved", position = 50)
    private Date approvedDate;
}
