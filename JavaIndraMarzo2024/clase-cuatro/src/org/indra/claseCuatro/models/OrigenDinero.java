package org.indra.claseCuatro.models;

public interface OrigenDinero {
	public abstract void extraer(DestinoDinero destino, double monto) throws SaldoInsuficienteException;
}
