package org.talentCamp.claseDos.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Excepcion no verificada
@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY)
public class ModelValidationException extends RuntimeException {
//Excepcion Verificada
//public class  ModelValidationException extends Exception {
    ModelValidationException(String message){
        super(message);
    }
}
