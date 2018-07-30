package com.teamproject.trainingrequest.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "TrainingRequest")
public class TrainingRequestEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String requestedByFirstName;
    private String requestedByLastName;
    private String location;
    private String description;
    private BigDecimal cost;
    private String approvedBy;
    private Date approvedDate;
    private Long employeeId;

}
