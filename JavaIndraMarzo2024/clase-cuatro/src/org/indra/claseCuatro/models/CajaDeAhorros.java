package org.indra.claseCuatro.models;

public class CajaDeAhorros extends Cuenta {

	//Estamos obligados a que sublclase tambien tenga constructor aunque solo llame al super
	public CajaDeAhorros(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void depositar(OrigenDinero origen, double monto) {
		if (monto<0) {
			throw new Error("El monto debe ser positivo");
		}
		
        this.movimientos.add(new Movimiento(monto,origen, this));		
	}

	@Override
	public void extraer(DestinoDinero destino, double monto) throws SaldoInsuficienteException {
		if (monto<0) {
			throw new Error("El monto debe ser positivo");
		}
		
        if(this.getSaldo()- monto <=0) { 
        	throw new SaldoInsuficienteException();
        }
        
        this.movimientos.add(new Movimiento(monto,this, destino));
        		
	}

}
