package org.ministerioTrabajo.claseunospring.services;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message) {
		super(message);
	}

}
