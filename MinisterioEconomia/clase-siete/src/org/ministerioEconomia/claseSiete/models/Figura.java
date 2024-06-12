package org.ministerioEconomia.claseSiete.models;

import java.util.UUID;

//Esta clase no se instancia, se usa para tener el codigo repetido
public abstract class Figura {
	protected UUID id;
	protected int x = 0;   //Inicializando una variable y dandole valor por defecto
	protected int y = 0;   //Inicializando una variable y dandole valor por defecto
	protected String nombre;
	
	public Figura() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
}
