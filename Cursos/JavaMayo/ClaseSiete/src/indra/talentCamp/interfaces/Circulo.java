package indra.talentCamp.interfaces;

public class Circulo extends Figura{

	private double radio;
	
	public Circulo(double radio) {
		super();
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}
	
	@Override
	public double calcularArea() {
		// TODO Auto-generated method stub
		return Math.PI * Math.pow(this.getRadio(), 2);
	}

	@Override
	public double calcularPerimetro() {
		// TODO Auto-generated method stub
		return Math.PI * 2 * this.radio;
	}
}
