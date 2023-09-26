package allianz.claseCinco.models;

public abstract class Figura {
	
	private int x = 0;
	private int y = 0;
	
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
