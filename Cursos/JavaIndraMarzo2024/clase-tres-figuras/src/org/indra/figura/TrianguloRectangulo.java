package org.indra.figura;

public class TrianguloRectangulo extends Figura {
	
	private double base;
	private double altura;

	public TrianguloRectangulo(int x, int y, double base, double altura) {
		super(x,y);
		this.base = base;
		this.altura = altura;
	}
	
	public TrianguloRectangulo(double base, double altura) {
		super();
		this.base = base;
		this.altura = altura;
	}


	public double getBase() {
		return base;
	}

	public double getAltura() {
		return altura;
	}
	
	public double getHipotenusa() {
		return Math.sqrt( Math.pow(altura, 2) + Math.pow(base,2));
	}

	@Override
	public double calcularArea() {
		return this.base * this.altura / 2;
	}

	@Override
	public double calcularPerimetro() {
		return this.base + this.altura + this.getHipotenusa();
	}

}
