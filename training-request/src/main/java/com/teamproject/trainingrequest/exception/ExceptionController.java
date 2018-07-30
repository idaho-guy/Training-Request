package com.teamproject.trainingrequest.exception;

import com.teamproject.trainingrequest.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import static java.util.stream.Collectors.joining;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllOtherExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TrainingRequestNotFoundException.class)
    public final ResponseEntity<Object> handleTrainingRequestNoFound(TrainingRequestNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handle(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(joining(", "));
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
