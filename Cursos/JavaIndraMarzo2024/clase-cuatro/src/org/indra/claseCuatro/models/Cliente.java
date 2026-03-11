package org.indra.claseCuatro.models;

import java.time.*;

public class Cliente extends ObjetoDeNegocio {

	private int dni;
	private String nombre;
	private String apellido;
	private LocalDate fechaDeNacimiento;
	
	public Cliente(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public int getDni() {
		return dni;
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

}
