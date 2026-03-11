package org.indra.claseTres.models;

import java.text.MessageFormat;

//refactoring
public class Estudiante extends Persona{
	
	//numeroDeAlumno (int), nombre, apellido, materiasAprobadas (int)
	//constructor nombre, apellido, getter todos, seeter para materias aprobadas
	private static int TOTAL_ALUMNOS = 0;
	private int numeroAlumno;
	//private String nombre;
	//private String apellido;
	private int materiasAprobadas;
	
	public Estudiante(String nombre, String apellido) {
		super(nombre, apellido);
		//this.nombre = nombre;
		//this.apellido = apellido;
		this.materiasAprobadas = 0;
		this.numeroAlumno = TOTAL_ALUMNOS + 1;
		Estudiante.TOTAL_ALUMNOS++;
	}

	public int getMateriasAprobadas() {
		return materiasAprobadas;
	}

	public void setMateriasAprobadas(int materiasAprobadas) {
		this.materiasAprobadas = materiasAprobadas;
	}

	public int getNumeroAlumno() {
		return numeroAlumno;
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
				Soy el estudiante numero {0} {1} {2}
				En total he aprobado {3} materias 
				""", this.numeroAlumno, this.nombre, this.apellido, this.materiasAprobadas));
	}
}

