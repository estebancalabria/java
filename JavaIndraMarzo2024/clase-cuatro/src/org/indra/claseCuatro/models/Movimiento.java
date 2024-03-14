package org.indra.claseCuatro.models;

public class Movimiento  extends ObjetoDeNegocio {

	private double valor;
	private TipoDeMovimiento tipo;	
	//Origen y Destino ??
	
	public Movimiento(double valor, TipoDeMovimiento tipo) {
		super();
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public double getValor() {
		return valor;
	}

	public TipoDeMovimiento getTipo() {
		return tipo;
	}
	
}
