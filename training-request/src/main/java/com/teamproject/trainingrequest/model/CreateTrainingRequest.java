package com.teamproject.trainingrequest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CreateTrainingRequest {

    @NotEmpty(message = "location is really required")
    @ApiModelProperty(required = true, value = "City in which the training is being held", position = 12)
    private String location;
    @NotEmpty(message = "message is really required")
    @Size(min = 9, message = "You should be more descriptive")
    @ApiModelProperty(required = true, value = "Description of requested training", position = 11)
    private String description;
    @NotNull(message = "cost must not be null")
    @ApiModelProperty(required = true, position = 13)
    private BigDecimal cost;
    @NotNull(message = "employeeId must not be null")
    @ApiModelProperty(required = true, value = "Unique of employee requesting training" ,position = 10)
    private Long employeeId;
}
