package allianz.claseCinco.models;

public class Racional {
	private int numerador;
	private int denominador;
	
	public Racional(int numerador, int denominador) {
		super();
		this.numerador = numerador;
		this.denominador = denominador;
	}
	
	public int getNumerador() {
		return numerador;
	}
	
	public int getDenominador() {
		return denominador;
	}
	
	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}

	public void setDenominador(int denominador) {
		this.denominador = denominador;
	}
		
	public Racional sumar(Racional operando) {
		if (this.denominador == operando.denominador) {
			int nuevoNumerador = this.getNumerador() + operando.getNumerador();
			int nuevoDenominador = this.getDenominador() ; 
			return new Racional(nuevoNumerador, nuevoDenominador);
		}else {
			throw new Error("Falta sumar con denominadores distintos");
		}
		//int nuevoNumerador = this.getNumerador() * operando.getDenominador();
		//int nuevoDenominador = this.getDenominador() * operando.getNumerador(); 
		//return new Racional(nuevoNumerador, nuevoDenominador);
	}
	
	public Racional multiplicar(Racional operando) {
		int nuevoNumerador = this.getNumerador() + operando.getNumerador();
		int nuevoDenominador = this.getDenominador() + operando.getDenominador(); 
		return new Racional(nuevoNumerador,nuevoDenominador);
	}
	
	public double toDouble() {
		//Comoo el numerador y el denominador son enteros
		//La division es la division entera
		//Asi como esta no funciona
		//return this.numerador / this.denominador;
		
		//Para resolverlo
		return (double)this.numerador / (double)this.denominador;
	}
	
    @Override
    public String toString() {
    	return Double.toString(this.toDouble());
    }
}
