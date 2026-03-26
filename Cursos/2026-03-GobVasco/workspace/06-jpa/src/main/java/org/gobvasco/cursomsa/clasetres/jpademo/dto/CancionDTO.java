package org.gobvasco.cursomsa.clasetres.jpademo.dto;

import jakarta.validation.constraints.*;

public class CancionDTO {

	private Long id;
	
	@NotBlank(message="El titulo de la cancion no puede quedar vacio")
	private String titulo;
	
	@Min(value=1, message="La puntuacion no puede ser menor a 1")
	@Max(value=10, message="La puntuacion no puede ser mayor a 10")
	@NotNull
	private int puntuacion;
	
	
	private String artista;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}	
}
