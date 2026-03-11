package org.ministerioTrabajo.claseunospring.models;

import java.time.LocalDate;


public class Persona extends ObjetoDeNegocio {
	
	//private int id;
	
	private String nombre;
	private int edad;
	//private string password <<< Definitivamente no se lo quiero mostrar al usuario Final!
	
	//De Uso Interno - Mejor que el usuario final no los vea
	//private LocalDate fechaCreacion;
	//private LocalDate fechaUltimaModificacion;
	
	public Persona() {
		super();
	}

	public Persona(String nombre, int edad) {
		this();
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Persona(int id, String nombre, int edad) {
		this(nombre, edad);
		this.setId(id);
	}
	
	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %d)", this.nombre, this.edad);
	}

	/*public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(LocalDate fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}*/


}
