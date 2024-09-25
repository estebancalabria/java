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
	
	@Override
	public double calcularArea() {
		// Esto no funciona. OJO!!!!!!!! No devuelve un int
		//return (this.base * this.altura) / 2;
		
		//Se soluciona asi...
		//return (this.base * this.altura) / 2.0;
		
		//O asi...
		return ((double)this.base * (double)this.altura) / 2;
	}
}
