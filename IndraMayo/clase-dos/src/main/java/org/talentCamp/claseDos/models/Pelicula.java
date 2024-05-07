package org.talentCamp.claseDos.models;

public class Pelicula extends ObjetoDeNegocio {
    private String nombre;
    private String genero;

    public Pelicula(String genero, String nombre) {
        this.genero = genero;
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
