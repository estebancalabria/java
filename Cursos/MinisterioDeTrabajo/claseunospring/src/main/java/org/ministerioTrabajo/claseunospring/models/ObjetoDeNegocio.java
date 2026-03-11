package org.ministerioTrabajo.claseunospring.models;

import java.time.LocalDate;

public class ObjetoDeNegocio {
	private int id;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaModificacion;
	
	public ObjetoDeNegocio() {
		this.fechaCreacion = LocalDate.now();
		this.fechaUltimaModificacion = LocalDate.now();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFechaCreacion() {
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
	}
}
