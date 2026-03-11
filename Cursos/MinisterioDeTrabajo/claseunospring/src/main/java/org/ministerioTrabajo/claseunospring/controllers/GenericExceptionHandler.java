package org.ministerioTrabajo.claseunospring.controllers;

import org.ministerioTrabajo.claseunospring.services.ElementoDuplicadoException;
import org.ministerioTrabajo.claseunospring.services.NoEncontradoException;
import org.ministerioTrabajo.claseunospring.services.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericExceptionHandler {
	
	@ExceptionHandler(NoEncontradoException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoEncontradoException(NoEncontradoException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(ElementoDuplicadoException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ResponseEntity<String> handleElementoDuplicadoException(ElementoDuplicadoException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}	
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleElementoDuplicadoException(ValidationException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
