package org.indra.figura;

public class Rectangulo extends Figura {

	private double ladoA;
	private double ladoB;

	public Rectangulo(int x, int y, double ladoA, double ladoB) {
		super(x, y);
		this.ladoA = ladoA;
		this.ladoB = ladoB;
	}

	public Rectangulo(double ladoA, double ladoB) {
		super();
		this.ladoA = ladoA;
		this.ladoB = ladoB;
	}

	public double getLadoA() {
		return ladoA;
	}

	public double getLadoB() {
		return ladoB;
	}

	@Override
	public double calcularArea() {
		// TODO Auto-generated method stub
		return this.ladoA + this.ladoB;
	}

	@Override
	public double calcularPerimetro() {
		// TODO Auto-generated method stub
		return (2 * this.ladoA) + (2 * this.ladoB);
	}

}
