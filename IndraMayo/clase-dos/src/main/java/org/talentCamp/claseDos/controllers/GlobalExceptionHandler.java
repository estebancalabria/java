package org.talentCamp.claseDos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.talentCamp.claseDos.services.NotFoundServiceException;

import java.util.NoSuchElementException;


//Tercer manera de manejar excepciones glogales para todos los controladores
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //ExceptionHandler(NoSuchElementException.class) Estaba asi y no funcionaba
    @ExceptionHandler(NotFoundServiceException.class)
    public ResponseEntity<String> handleElementoNoEncontrado() {

        //En vez de un System.out.println voy a usar un logger que queda mas profesional
        //System.out.println("Se ejectuto el GlobalExceptionHandler");
        logger.info("Se ejectuto el GlobalExceptionHandler");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
    }

}
