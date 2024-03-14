package org.indra.figura;

public abstract class Figura implements AreaCalculable {
	private int x;
	private int y;
	
	public Figura(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Figura() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract double calcularArea();
	
	public abstract double calcularPerimetro();
	
}
