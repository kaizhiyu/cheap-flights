package com.cheapflights.tickets.controller;

import com.cheapflights.tickets.domain.dto.ResponseMessageDTO;
import com.cheapflights.tickets.exception.AirportsNotImportedException;
import com.cheapflights.tickets.exception.UpdateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ResponseMessageDTO>> fieldConstraintsViolationException(ConstraintViolationException e) {
        List<ResponseMessageDTO> errors = e.getConstraintViolations().stream()
                .map(constraintViolation -> new ResponseMessageDTO(constraintViolation.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(UpdateEntityException.class)
    public ResponseEntity<ResponseMessageDTO> updateEntityException(UpdateEntityException e) {
        return ResponseEntity.badRequest().body(new ResponseMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(AirportsNotImportedException.class)
    public ResponseEntity<ResponseMessageDTO> airportsNotImportedException(AirportsNotImportedException e) {
        return ResponseEntity.badRequest().body(new ResponseMessageDTO(e.getMessage()));
    }
}
