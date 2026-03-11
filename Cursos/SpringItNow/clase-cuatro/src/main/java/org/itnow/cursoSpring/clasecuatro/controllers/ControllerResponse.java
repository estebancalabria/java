package org.itnow.cursoSpring.clasecuatro.controllers;

import lombok.*;

@Builder
public class ControllerResponse {
	@Getter @Setter
	private boolean success;
	
	@Getter @Setter
	private String operacion;
	
	@Getter @Setter
	private String mensaje;
	
	@Getter @Setter
	private Object entidadAfectada;

}
