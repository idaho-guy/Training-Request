package com.teamproject.trainingrequest.repository;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRequestRepository extends CrudRepository<TrainingRequestEntity, Long> {
}
