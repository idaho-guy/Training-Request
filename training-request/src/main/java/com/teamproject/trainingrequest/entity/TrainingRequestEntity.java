package com.teamproject.trainingrequest.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "TrainingRequest")
public class TrainingRequestEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String requestedByFirstName;
    @NotNull
    private String requestedByLastName;
    @NotNull
    private String location;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal cost;
    @NotNull
    private Long employeeId;
    private String approvedBy;
    private Date approvedDate;

}
