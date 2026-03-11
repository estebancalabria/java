package org.indra.claseSeis.models;

import java.time.LocalDate;

public class Cliente extends ObjetoDeNegocio {

	private int dni;
	private LocalDate fechaNacimiento;

	private String nombre;
	private String apellido;

	public int getDni() {
		return dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	// Visibles dentro del paquete nomas
	void setDni(int dni) {
		this.dni = dni;
	}

	void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public static ClienteBuilder builder() {
		return new ClienteBuilder();
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		cadena.append("El cliente se llama: " + this.nombre + "\n");
		cadena.append("Con apellido: " + this.apellido + "\n");
		cadena.append("Con DNI: " + this.dni + "\n");
		cadena.append("Con fecha: " + this.fechaNacimiento + "\n");
		return cadena.toString();
	}

}
