package org.indra.claseDos.models;

public class Automovil {
 
	//Con 4 atributos privados
	private String matricula;
    private Color color;
    private String modelo;
    private int a�o;
    
	/**
	 * @param matricula del automovil
	 * @param color
	 * @param modelo
	 * @param a�o
	 */
	public Automovil(String matricula, Color color, String modelo, int a�o) {
		super();
		this.matricula = matricula;
		this.color = color;
		this.modelo = modelo;
		this.a�o = a�o;
	}
	
}
