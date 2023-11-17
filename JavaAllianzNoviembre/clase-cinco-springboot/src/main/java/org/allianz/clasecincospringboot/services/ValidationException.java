package org.allianz.clasecincospringboot.services;

public class ValidationException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message) {
		super(message);
	}

}
