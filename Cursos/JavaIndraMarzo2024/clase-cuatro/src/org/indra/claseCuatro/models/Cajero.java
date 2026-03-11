package org.indra.claseCuatro.models;

public class Cajero implements DestinoDinero, OrigenDinero{

	@Override
	public void extraer(DestinoDinero destino, double monto) throws SaldoInsuficienteException {
		throw new Error("Not implemented");
	}

	@Override
	public void depositar(OrigenDinero origen, double monto) {
		throw new Error("Not implemented");
	}

}
