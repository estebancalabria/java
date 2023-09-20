package org.indra.models;

import java.time.*;

public class Pelicula extends ObjetoDeNegocio{
	private String titulo;
	private LocalDate fecha;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
