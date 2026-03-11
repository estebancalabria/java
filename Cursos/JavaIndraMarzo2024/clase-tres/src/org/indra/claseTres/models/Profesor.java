package org.indra.claseTres.models;

import java.text.MessageFormat;

public class Profesor extends Persona {
   //dni, nombre, apellido, cursosImpartidos
	//constructor, nombre, apellido, dni
	//todo tiene getter, cursoImpartidos setter
	private int dni;
	//private String nombre;
	//private String apellido;
	private int cursosImpartidos;
	
	public Profesor(int dni, String nombre, String apellido) {
		super(nombre, apellido);
		this.dni = dni;
		//this.nombre = nombre;
		//this.apellido = apellido;
		this.cursosImpartidos = 0;
	}

	public int getCursosImpartidos() {
		return cursosImpartidos;
	}

	public void setCursosImpartidos(int cursosImpartidos) {
		this.cursosImpartidos = cursosImpartidos;
	}

	public int getDni() {
		return dni;
	}

	/*public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}*/
	
	@Override
	public void saludar() {
        System.out.println(MessageFormat.format("""
                Soy el profesor con dni {0} {1} {2}
                En total imparto {3} materias
                """, this.dni, this.nombre, this.apellido, this.cursosImpartidos));		
	}
	
}
