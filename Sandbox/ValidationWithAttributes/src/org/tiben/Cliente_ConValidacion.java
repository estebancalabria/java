package org.tiben;

public class Cliente_ConValidacion {
	
	private String nombre;
	//...

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void validar() {
		if ((this.nombre==null) || this.nombre.trim().length()==0) {
			throw new Error("El nombre no puede quedar vacio.");
		}
		
		//Repetimos el codigo para todas las validaciones...
		//Codigo Boilerplate!
	}
}
