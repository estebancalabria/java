package org.itnow.cursoSpring.clasetres.models;

import lombok.*;

@ToString()
public class Automovil {
	
	@Getter @Setter
	private String marca;
	
	@Getter @Setter
	private String modelo;
	
	@Getter @Setter
	private int año;
	
}
