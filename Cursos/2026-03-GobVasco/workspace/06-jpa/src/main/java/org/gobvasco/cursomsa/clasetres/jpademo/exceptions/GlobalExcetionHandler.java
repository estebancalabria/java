package org.gobvasco.cursomsa.clasetres.jpademo.exceptions;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcetionHandler {
   
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> manejarErrores(MethodArgumentNotValidException ex){
		Map<String, String> errores = new HashMap<>();
		
		errores.put("error", "Se ha producido un error de Validacion");
		
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errores.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errores);		
				
	}
}
