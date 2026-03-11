package org.indra.lambda;

public class Persona {
    private String nombre;
	private int edad;
	
    public Persona(String nomnbre, int edad) {
		super();
		this.nombre = nomnbre;
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}
    
	@Override
	public String toString() {
		return this.getNombre();
	}
    
}
