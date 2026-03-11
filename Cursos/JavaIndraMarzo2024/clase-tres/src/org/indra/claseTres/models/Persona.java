package org.indra.claseTres.models;

public abstract class Persona {

	//nombre, apellido, constructor, getter, saludar abstracto
	protected String nombre;
    protected String apellido;
    
    public Persona(String nombre, String apellido) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public abstract void  saludar();
}
