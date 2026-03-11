package org.indra.claseCuatro.models;

public class CuentaCorriente extends Cuenta {
	
	private double maximo; //La cuenta no puede pasarse de eso
	private double saldoDescubierto; //puede tener valores negativos
	
	//Se animan a completarla uds?
    public CuentaCorriente(Cliente cliente, double maximo, double saldoDescubierto) {
        super(cliente);
        this.maximo = maximo;
        this.saldoDescubierto = saldoDescubierto;
    }
    
    public double getMaximo() {
        return maximo;
    }
    
    public double getSaldoDescubierto() {
        return saldoDescubierto;
    }
    
    @Override
    public void depositar(OrigenDinero origen,double cantidad) {
        if (cantidad <= 0) {
            throw new Error("La cantidad debe ser positiva.");
        }
        
        if (cantidad  + this.getSaldo() > maximo) {
            throw new Error("La cantidad no puede superar el maximo.");
        }
        
        //Vamos a cambiar esto en breve lo del null
        this.movimientos.add(new Movimiento(cantidad, origen, this));
    }
    
    @Override
    public void extraer(DestinoDinero destino, double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0) {
            throw new Error("La cantidad debe ser positiva.");
        }
        
        if(this.getSaldo()- cantidad <= this.saldoDescubierto) { 
        	throw new SaldoInsuficienteException();
        }
        
        this.movimientos.add(new Movimiento(cantidad, this, destino));
        
    }

}
