package org.indra.claseOcho.models;

import lombok.*;

@ToString
public class Movil extends BusinessObject {
	
	@Getter @Setter
	private String marca;
	
	@Getter @Setter
	private int tamaño;
}
