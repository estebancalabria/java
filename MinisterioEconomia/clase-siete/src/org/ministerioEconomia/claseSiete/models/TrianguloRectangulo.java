package org.ministerioEconomia.claseSiete.models;

public class TrianguloRectangulo extends Figura {

	private int base;
	private int altura;

    private TrianguloRectangulo() {
		super();
		this.nombre = "TrianguloRectangulo";
	}

	public TrianguloRectangulo(int base, int altura) {
		this();
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public String toString() {
		return "Soy un Triangulo con base " + this.base + " y altura " + this.altura;
	}
}
