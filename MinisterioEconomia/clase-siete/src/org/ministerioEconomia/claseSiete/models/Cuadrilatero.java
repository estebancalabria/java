package org.ministerioEconomia.claseSiete.models;

public class Cuadrilatero extends Figura {

	private int lado1;
	private int lado2;

	private Cuadrilatero() {
		super();
		this.nombre = "Cuadrilatero";
	}

	public Cuadrilatero(int lado1, int lado2) {
		this();
		this.lado1 = lado1;
		this.lado2 = lado2;
	}

	public String toString() {
		return "Soy un " + this.nombre + " con lado1 " + this.lado1 + " y lado2 " + this.lado2;
	}

}
