package indra.talentCamp.relaciones.composite;

public class Multiplicacion extends OperacionBinaria{
	
    public Multiplicacion(double operandoIzquierdo, double operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}


	public Multiplicacion(double operandoIzquierdo, Operacion operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}


	public Multiplicacion(Operacion operandoIzquierdo, double operandoDerecho) {
		super(operandoIzquierdo, operandoDerecho);
		// TODO Auto-generated constructor stub
	}


	public Multiplicacion(Operacion operandoIzq, Operacion operacionDer) {
        super(operandoIzq, operacionDer);
    }
    
    
    @Override
    public double calcular() {
        return this.operandoIzquierdo.calcular() * this.operandoDerecho.calcular();
    }
}
