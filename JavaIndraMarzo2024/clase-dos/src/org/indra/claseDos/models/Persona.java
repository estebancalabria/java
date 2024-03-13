package org.indra.claseDos.models;

import java.text.MessageFormat;

//Que solo se puedan modificar los atrinutos desde la propia clase y no desde otros sit
//===> Separar el estado interno de un objeto de su interfaz
public class Persona {
	//atributos
    //public String nombre;
    //public String apellido;
    //public int edad;
    
	//Los convierto en privados para respetar el encapsulamiento 
    private String nombre;
    private String apellido;
    private int edad;
    
    public Persona(String nombre, String apellido, int edad) {
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.edad = edad;
    }
    
    //Sobrecarga de constructores
    public Persona(String nombre, String apellido) {
    	//Algunos usan el setter y lo llaman "doble encapsulamiento"
    	//this.setNombre(nombre);
    	//this.setApellido(apellido);    	
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.edad = 0;
    }
    
    //
    // Como agregue un constructor que crea el objeto en forma consitente
    // entoces decido sacar los setter de setNombre y setApellido para evitar
    // que el objeto se rompa
    //
    /*public void setNombre(String value) {
    	if ((value == null) || (value.trim().isEmpty())) {
    		throw new Error("El nombre no puede ser vacio");
    	}
    	this.nombre = value;
    }
    
    //Alternativa, usar exception y tengo que avisar
    /*public void setNombre(String value) throws Exception {
    	if ((value == null) || (value.trim().length()==0)) {
    		throw new Exception("El nombre no puede ser vacio");
    	}
    	this.nombre = value;
    }*/
    
    /*public void setApellido(String apellido) {
    	if ((apellido==null) || (apellido.trim().isEmpty())) {
    		throw new Error("El apellido no puede ser vacio");
    	}
        this.apellido = apellido;
    }*/
    
    public void setEdad(int edad) {
    	if ((edad<=0) || (edad>=130)) {
    		throw new Error("La edad esta fuera de rango");
    	}
        this.edad = edad;
    }
    
    //
    //Si mi objetivo es mostrar el objeto
    //sobrescribiendo el toString me puedo ahorrar el setNombre y setApellico
    //
    /*public String getNombre() {
        return this.nombre;
    }
    
    public String getApellido() {
        return this.apellido;
    }*/
    
    public int getEdad() {
        return this.edad;
    }
    
    @Override
    public String toString() {
    	return MessageFormat.format("Soy {0} {1} de {2} años", 
    			this.nombre,
    			this.apellido,
    			this.edad);    	
    }
    
    
}
