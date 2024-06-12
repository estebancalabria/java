package org.ministerioEconomia.models;

import java.util.UUID;

public class Departamento extends ObjetoDeNegocio{
	//private UUID id;
	private String nombre;


	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
		//this.id = UUID.randomUUID();
	}

	/*public UUID getId() {
		return id;
	}*/	
		
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + "]";
	}



}
