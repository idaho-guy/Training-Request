package com.teamproject.trainingrequest.exception;

public class TrainingRequestNotFoundException extends RuntimeException {

    public TrainingRequestNotFoundException(Long trainingRequestId) {
        super(String.format("Training request with id %d not found", trainingRequestId));
    }
}
