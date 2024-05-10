package org.talentCamp.claseUnoSpringData.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.talentCamp.claseUnoSpringData.services.ServiceValidationException;
import java.util.*;

@RestControllerAdvice
public class ManejadorDeExcepciones {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ServiceValidationException.class)
    public ResponseEntity<Object> handleServiceValidatorException(ServiceValidationException ex){

        //Quiero devolver un json porque angular no se lleva muy bien con los string
        return ResponseEntity.unprocessableEntity().body(
                Map.of("mensaje", ex.getMessage())
        );
    }
}
