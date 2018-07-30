package com.teamproject.trainingrequest.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CreateTrainingRequest {

    @NotEmpty(message = "location is really required")
    private String location;
    @NotEmpty(message = "message is really required")
    @Size(min = 12, message = "You should be more descriptive")
    private String description;
    @NotNull(message = "cost must not be null")
    private BigDecimal cost;
    @NotNull(message = "employeeId must not be null")
    private Long employeeId;
}
