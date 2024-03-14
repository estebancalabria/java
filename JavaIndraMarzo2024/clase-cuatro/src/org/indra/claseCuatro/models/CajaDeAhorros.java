package org.indra.claseCuatro.models;

public class CajaDeAhorros extends Cuenta {

	//Estamos obligados a que sublclase tambien tenga constructor aunque solo llame al super
	public CajaDeAhorros(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void depositar(double monto) {
		if (monto<0) {
			throw new Error("El monto debe ser positivo");
		}
		
        this.movimientos.add(new Movimiento(monto,TipoDeMovimiento.Entrada));		
	}

	@Override
	public void extraer(double monto) throws SaldoInsuficienteException {
		if (monto<0) {
			throw new Error("El monto debe ser positivo");
		}
		
        if(this.getSaldo()- monto <=0) { 
        	throw new SaldoInsuficienteException();
        }
        
        this.movimientos.add(new Movimiento(monto,TipoDeMovimiento.Salida));
        		
	}

}
