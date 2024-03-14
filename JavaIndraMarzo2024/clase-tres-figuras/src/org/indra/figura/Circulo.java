package org.indra.figura;

public class Circulo extends Figura implements AreaCalculable {
	
	double radio;

	public Circulo(int x, int y, double radio) {
		super(x, y);
		this.radio = radio;
	}


	public Circulo(double radio) {
		super();
		this.radio = radio;
	}
	
	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}


	@Override
	public double calcularArea() {
		// TODO Auto-generated method stub
		return Math.PI * Math.pow(radio, 2.0);
	}

	@Override
	public double calcularPerimetro() {
		return 2 * Math.PI * this.radio;
	}

}
