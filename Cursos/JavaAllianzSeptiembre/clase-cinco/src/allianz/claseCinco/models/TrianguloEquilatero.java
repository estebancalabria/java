package allianz.claseCinco.models;

public class TrianguloEquilatero extends Triangulo{

	//Si la clase padre tiene un constructor
	//Estoy obligado a tener uno
	//Aunque solo llame al super
	public TrianguloEquilatero(double lado) {
		super(lado, lado);
	}

	@Override
	public double calcularPerimetro() {
		// TODO Auto-generated method stub
		return this.getBase() * 3;
	}

}
