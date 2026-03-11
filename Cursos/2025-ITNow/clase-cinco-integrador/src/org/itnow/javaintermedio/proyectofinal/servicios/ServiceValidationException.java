package org.itnow.javaintermedio.proyectofinal.servicios;

public class ServiceValidationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceValidationException(String mensaje) {
		super("Error de Validacion en Servicio : " + mensaje);
	}
}
