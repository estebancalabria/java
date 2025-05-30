package itnow.javaintermedio.clasetres.controllers;

public class Vista {

	private String contenido;
	
	public Vista(String contenido) {
		super();
		this.contenido = contenido;
	}	

	@Override
	public String toString() {
		return this.contenido;
	}
}
