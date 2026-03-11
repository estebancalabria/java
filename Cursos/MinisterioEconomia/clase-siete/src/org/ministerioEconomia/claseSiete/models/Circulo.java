package org.ministerioEconomia.claseSiete.models;

public class Circulo extends Figura {

	private int radio = 0;

	private Circulo() {
		super();
		this.nombre = "Circulo";
	}

	// Para obligar a que se defina el radio al hacer circulo
	public Circulo(int radio) {
		this();
		this.radio = radio;
	}

	/*
	 * public Circulo() { this.nombre = "Circulo"; }
	 */

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Soy un " + this.nombre + "de radio " + this.radio;
	}
	
	@Override
	public double calcularArea() {
		return Math.PI * Math.pow(this.radio, 2);
	}
}
