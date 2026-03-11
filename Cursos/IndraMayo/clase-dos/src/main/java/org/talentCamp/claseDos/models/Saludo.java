package org.talentCamp.claseDos.models;

public class Saludo {
    private String mensaje;
    private int prioridad;
    private String autor;

    public Saludo(String mensaje, int prioridad, String autor) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
