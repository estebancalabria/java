package indra.talentCamp.relaciones.composite;


public abstract class OperacionBinaria extends Operacion {


	protected Operacion operandoIzquierdo;
	protected Operacion operandoDerecho;
	
	public OperacionBinaria(Operacion operandoIzquierdo, Operacion operandoDerecho) {
		super();
		this.operandoIzquierdo = operandoIzquierdo;
		this.operandoDerecho = operandoDerecho;
	}	
	
	public OperacionBinaria(double operandoIzquierdo, double operandoDerecho) {
		this.operandoIzquierdo = new Valor(operandoIzquierdo);
		this.operandoDerecho = new Valor(operandoDerecho);
	}
		
	public OperacionBinaria(double operandoIzquierdo, Operacion operandoDerecho) {
		super();
		this.operandoIzquierdo = new Valor(operandoIzquierdo);
		this.operandoDerecho = operandoDerecho;
	}
	
	public OperacionBinaria(Operacion operandoIzquierdo, double operandoDerecho) {
		super();
		this.operandoIzquierdo = operandoIzquierdo;
		this.operandoDerecho = new Valor(operandoDerecho);
	}	

}
