package indra.talentCamp.relaciones.composite;

public class Suma extends OperacionBinaria {

	public Suma(double operandoIzquierdo, double operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}

	public Suma(double operandoIzquierdo, Operacion operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}

	public Suma(Operacion operandoIzquierdo, double operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}

	public Suma(Operacion operandoIzquierdo, Operacion operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
	}

	@Override
	public double calcular() {
		// TODO Auto-generated method stub
		return this.operandoIzquierdo.calcular() + this.operandoDerecho.calcular();
	}

}
