package org.indra.claseCuatro.models;

import java.time.LocalDate;

public class Movimiento  extends ObjetoDeNegocio {

	private double valor;
	//private TipoDeMovimiento tipo;	
	//Origen y Destino ??
	private LocalDate fecha;
	private OrigenDinero origen;
	private DestinoDinero destino;
	
	public Movimiento(double valor, OrigenDinero origen, DestinoDinero destino) {
		super();
		this.valor = valor;
		this.origen = origen;
		this.destino = destino;
		this.fecha = LocalDate.now();
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public OrigenDinero getOrigen() {
		return origen;
	}

	public DestinoDinero getDestino() {
		return destino;
	}

	public double getValor() {
		return valor;
	}
	
}
