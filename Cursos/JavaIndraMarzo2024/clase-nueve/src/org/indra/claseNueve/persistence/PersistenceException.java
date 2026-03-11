package org.indra.claseNueve.persistence;

import lombok.Getter;

public class PersistenceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Exception innerException;

	public PersistenceException(String message, Exception innerException) {
		super(message);
		this.innerException = innerException;
	}
}
