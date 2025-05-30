package itnow.javaIntermedio.clasesUno;

public class Cuenta {
	double saldo;

	public Cuenta(double saldoInicial) {
		this.saldo = saldoInicial;
	}

	public void depositar(double cantidad) {
		
		//Que pasa si le paso un monto negativo?
		//Es mas "raro" que le pasen monto negatico, puedo usar una excepcion no tipada
		if (cantidad<0) {
			throw new IllegalArgumentException("No se puede depositar montos negativos");
		}
		
		this.saldo += cantidad;
	}

	// Quiero que el extraer lanze un error si extraigo mas de lo que tengo....
	public void extraer(double cantidad) throws SaldoInsuficienteException {

		if ((cantidad > this.saldo)) {
			throw new SaldoInsuficienteException();
		}

		this.saldo -= cantidad;
	}

	public double getSaldo() {
		return saldo;
	}
}
