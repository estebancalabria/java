package org.itnow.cursoSpring.clasecuatro.models;

import lombok.*;

@ToString()
@AllArgsConstructor()
@NoArgsConstructor()
public class Automovil extends ObjetoModels {
	
	@Getter @Setter
	private String marca; 
	
	@Getter @Setter
	private String modelo;
	
	@Getter @Setter
	private int año;
}
