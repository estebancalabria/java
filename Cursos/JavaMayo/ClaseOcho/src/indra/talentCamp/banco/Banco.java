package indra.talentCamp.banco;

import java.util.*;

public class Banco {
    
	private List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();

	public List<CuentaBancaria> getCuentas() {
		return Collections.unmodifiableList(cuentas);
	}
   
	public CuentaBancaria crearCajaDeAhorrro(Cliente cliente) {
		CajaDeAhorro cuenta = new CajaDeAhorro(cliente);
		this.cuentas.add(cuenta);
		return cuenta;
	}
	
	public CuentaBancaria crearCuentaCorriente(Cliente cliente) {
		CuentaCorriente cuenta = new CuentaCorriente(cliente);
		this.cuentas.add(cuenta);
		return cuenta;
	}
	
	public CuentaBancaria buscarCuenta(int numeroCuenta) {		
	  return this.cuentas.stream().filter(c -> c.getNumeroCuenta() == numeroCuenta).findFirst().orElse(null);	
	}
}
