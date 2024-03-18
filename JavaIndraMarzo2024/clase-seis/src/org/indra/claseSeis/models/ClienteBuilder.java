package org.indra.claseSeis.models;

import java.time.LocalDate;

//Builder que implementa interfaz fluida o fluent interfaces
public class ClienteBuilder {
	private int id;
	private int dni;
	private LocalDate fechaNacimiento;
	private String nombre = null;
	private String apellido = null;
	
	public ClienteBuilder withId(int id) {
		this.id = id;
		return this;
	}

	public ClienteBuilder withDni(int dni) {
		this.dni = dni;
		return this;
	}

	public ClienteBuilder withNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public ClienteBuilder withApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public ClienteBuilder withFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		return this;
	}

	public Cliente build() {
		//Validaciones
		if ((nombre==null) || (nombre.trim().length()==0)) {
			throw new Error("Falta el nombre");
		}
		//Asi ponemos todas las validaciones que hacemos a la hora de construir
		
		Cliente c = new Cliente();
		c.setId(this.id);
		c.setDni(this.dni);
		c.setFechaNacimiento(this.fechaNacimiento);
		c.setNombre(this.nombre);
		c.setApellido(this.apellido);
		return c;
	}
}
