package org.indra.claseDos.models;

public class Automovil {
 
	//Con 4 atributos privados
	private String matricula;
    private Color color;
    private String modelo;
    private int año;
    
	/**
	 * @param matricula del automovil
	 * @param color
	 * @param modelo
	 * @param año
	 */
	public Automovil(String matricula, Color color, String modelo, int año) {
		super();
		this.matricula = matricula;
		this.color = color;
		this.modelo = modelo;
		this.año = año;
	}
	
}
