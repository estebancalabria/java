package allianz.claseCuatro.models;

import java.time.*;
import java.util.*;

public class Alumno extends Persona{
	//Refactoring 4 - Herencia
	//private String nombre;
    //private String apellido;
	//private Sexo sexo;
	//private LocalDate fechaDeNacimiento;
	
	private List<String> cursos;
	
	//REGLA DE JAVA DE CONSTRUCTORES:
	//  SI UNA CLASE BASE DEFINE UN CONSTRUCTOR NO VACIO
	//  LA CLASE HIJA ESTA OBLIGADA A DEFINIR UN CONSTRUCTOR
	//  (AUNQUE LO UNICO QUE HAGA ESE CONSTRUCTOR SEA LLAMAR AL SUPER)
	public Alumno(String nombre, String apellido, Sexo sexo, LocalDate fechaDeNacimiento) {
		super(nombre, apellido, sexo, fechaDeNacimiento);
		//this.nombre = nombre;
		//this.apellido = apellido;
		//this.sexo = sexo;
		//this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public Alumno(String nombre, String apellido, Sexo sexo) {
		this(nombre, apellido, sexo, LocalDate.now());
	}
	
	public Alumno(String nombre, String apellido) {
		this(nombre, apellido, Sexo.NO_DEFINIDO, LocalDate.now());
	}

	//Refactoring 4 - Herencia
	/*public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public List<String> getCursos() {
		return cursos;
	}*/
	
	public int getEdad() {
		//Refactoring 4 - Herencia
		//Tengo un problema.
		// Solucion 1 (Recomendada) : Llamar al getFechaDeNacimiento
		//		Period periodo = Period.between(LocalDate.now(), this.getFechaDeNacimiento());
		//
		// Solucion 2 (No es la recomendada pero es la que vamos a hacer) 
		//           : Es cambiarle el nivel de visibilidad a la fecha de nacimiento a protected en Persona
		Period periodo = Period.between(LocalDate.now(), this.fechaDeNacimiento);
		return Math.abs(periodo.getYears());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Alumno %s %s de %d años", 
				this.getNombre(),
				this.getApellido(),
				this.getEdad());
	}
	
	
}
