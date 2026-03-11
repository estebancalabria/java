package org.ministerioTrabajo.claseunospring.models;

import java.time.LocalDate;

public class Automovil extends ObjetoDeNegocio {
	private String matricula;
	private String modelo;
	//private LocalDate anio;
	
	public Automovil(int id, String matricula, String modelo/*, LocalDate anio*/) {
		super();
		this.setId(id);
		this.matricula = matricula;
		this.modelo = modelo;
		//this.anio = anio;
	}
	
	public Automovil() {
		super();
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/*public LocalDate getAnio() {
		return anio;
	}
	
	public void setAnio(LocalDate anio) {
		this.anio = anio;
	}*/
}
