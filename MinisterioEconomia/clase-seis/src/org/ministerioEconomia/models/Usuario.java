package org.ministerioEconomia.models;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.UUID;

public class Usuario extends ObjetoDeNegocio {

	//private UUID id; /* readonly */
	private String nombre;
	private Departamento departamento;
	private LocalDate fechaDeNacimiento;
	private Sexo sexo;

	public Usuario(String nombre, Departamento departamento, LocalDate fechaDeNacimiento, Sexo sexo) {
		super();
		//this.id = UUID.randomUUID();
		this.nombre = nombre;
		this.departamento = departamento;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.sexo = sexo;
	}/* constructor */
	
	public Usuario(String nombre, Departamento departamento, LocalDate fechaDeNacimiento) {
		this(nombre, departamento, fechaDeNacimiento, Sexo.Otro);
	}

	/*
	 * Cuando una clase tiene varios constructores se llama
	 * "sobrecarga de constructores"
	 */
	public Usuario(String nombre, Departamento departamento) {
		this(nombre, departamento, LocalDate.now(), Sexo.Otro); /* El this en este caso me sirve para llamar al otro constructor */
	}/* constructor */

	//Factory Methods : Otra alternativa para crear objetos particulares
	public static Usuario superUsuario() {
		return new Usuario("SuperUsuario", new Departamento("Olimpo"), LocalDate.MIN);
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("[{0}] Nombre: {1} ,Departamento: {2}, NacidoEn: {3}", 
				this.id, 
				this.nombre, 
				this.departamento, 
				this.fechaDeNacimiento);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.isBlank()) {
			throw new Error("El nombre del usuario no puede quedar vacio");
		}
		
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/*public UUID getId() {
		return id;
	}*/

} /* class Usuario */
