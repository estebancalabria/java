package org.itnow.cursoSpring.claseUno.models;

public class Cliente {
	int documento;
	String nombre;
	
	
	public Cliente(int documento, String nombre) {
		super();
		this.documento = documento;
		this.nombre = nombre;
	}
	
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
