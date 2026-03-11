package org.ministerioTrabajo.claseunospring.services;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoEncontradoException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public NoEncontradoException(String message) {
		super(message);
	}
	
}
