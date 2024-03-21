package org.indra.claseOcho.models;

import lombok.*;

@AllArgsConstructor @ToString
public class ModelValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String attribute;
	private String reason;
}
