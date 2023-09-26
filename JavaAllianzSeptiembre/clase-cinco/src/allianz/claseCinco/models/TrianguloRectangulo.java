package allianz.claseCinco.models;

public class TrianguloRectangulo extends Triangulo {

	public TrianguloRectangulo(double base, double altura) {
		super(base, altura);
	}
	
	public double getHipotenusa() {
		return Math.sqrt( Math.pow(this.getAltura(), 2) + Math.pow(this.getBase(), 2));
	}

	@Override
	public double calcularPerimetro() {
		// TODO Auto-generated method stub
		return this.getBase() 
				+ this.getAltura() 
				+ this.getHipotenusa();
	}

}
