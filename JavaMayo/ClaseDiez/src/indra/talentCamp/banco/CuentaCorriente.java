package indra.talentCamp.banco;

public class CuentaCorriente extends CuentaBancaria {

     CuentaCorriente(Cliente propietario) {
		super(propietario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void extraer(double monto) throws SaldoInsuficienteException{
        this.movimientos.add(new Movimiento(-monto));
	}

	@Override
	public void extraer(double monto, String concepto) throws SaldoInsuficienteException {
        this.movimientos.add(new Movimiento(-monto, concepto));
	}
	
	

}
