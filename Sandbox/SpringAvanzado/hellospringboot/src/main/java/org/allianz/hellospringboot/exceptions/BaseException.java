 package org.allianz.hellospringboot.exceptions;

public class BaseException extends RuntimeException {
	public BaseException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;
}
