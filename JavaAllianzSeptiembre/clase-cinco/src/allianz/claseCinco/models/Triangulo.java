package allianz.claseCinco.models;

public abstract class Triangulo extends Figura {

	private double base;
	private double altura;

	public Triangulo(double base, double altura) {
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
	
	@Override
	public double calcularArea() {
		return this.base * this.altura / 2;
	}
}
