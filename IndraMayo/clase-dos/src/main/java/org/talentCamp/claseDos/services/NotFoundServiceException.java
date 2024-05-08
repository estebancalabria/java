package org.talentCamp.claseDos.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Elemento no encontrado")
public class NotFoundServiceException extends Exception {

    public  NotFoundServiceException(){
        super("Elemento no encontrado");
    }
}
