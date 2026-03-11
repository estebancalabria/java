package org.indra.models;

import java.time.LocalDate;

public class Pelicula extends ObjetoDeNegocio {
	
    private String titulo;
    private int duracion;
    private LocalDate fechaSalida;
    private String director;
    
    public Pelicula(String titulo, int duracion, LocalDate fechaSalida, String director) {
        super();
        this.titulo = titulo;
        this.duracion = duracion;
        this.fechaSalida = fechaSalida;
        this.director = director;
    }
    
    public Pelicula() {
    	super();
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }    
}
