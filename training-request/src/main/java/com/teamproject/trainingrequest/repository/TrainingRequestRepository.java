package com.teamproject.trainingrequest.repository;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TrainingRequestRepository extends CrudRepository<TrainingRequestEntity, Long> {

    List<TrainingRequestEntity> getTrainingRequestEntitiesByCostAfter(BigDecimal cost);

    @Query("from TrainingRequest where approvedBy is null ")
    List<TrainingRequestEntity> getOpenTrainingRequests();


}
