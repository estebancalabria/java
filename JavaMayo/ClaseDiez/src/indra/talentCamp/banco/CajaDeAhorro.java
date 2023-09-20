package indra.talentCamp.banco;

public class CajaDeAhorro extends CuentaBancaria{

     CajaDeAhorro(Cliente propietario) {
		super(propietario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void extraer(double monto) throws SaldoInsuficienteException{
        if (this.getSaldo() < monto) {
        	   throw new SaldoInsuficienteException();
        }
        
        this.movimientos.add(new Movimiento(-monto));
	}

	@Override
	public void extraer(double monto, String concepto) throws SaldoInsuficienteException {
        if (this.getSaldo() < monto) {
     	   throw new SaldoInsuficienteException();
        }
     
        this.movimientos.add(new Movimiento(-monto, concepto));
	}

}
