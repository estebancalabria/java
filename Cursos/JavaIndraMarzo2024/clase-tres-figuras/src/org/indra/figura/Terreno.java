package org.indra.figura;

public class Terreno implements AreaCalculable{
	
	double largo;
	double ancho;

	public Terreno(double largo, double ancho) {
		super();
		this.largo = largo;
		this.ancho = ancho;
	}

	public double getLargo() {
		return largo;
	}

	public double getAncho() {
		return ancho;
	}

	public double calcularArea() {
		// TODO Auto-generated method stub
		return this.ancho * this.largo;
	}

}
