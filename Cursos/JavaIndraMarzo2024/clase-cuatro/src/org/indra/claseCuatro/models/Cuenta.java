package org.indra.claseCuatro.models;

import java.time.*;
import java.util.*;

public abstract class Cuenta  extends ObjetoDeNegocio implements OrigenDinero, DestinoDinero {

	private static int ULTIMO_NUMERO_CUENTA = 0;
	private int numeroDeCuenta;
	//asociacion-relacion
	private Cliente cliente;
	private LocalDate fechaDeCreacion;
	//composicion
	protected List<Movimiento> movimientos;
	
	public Cuenta(Cliente cliente) {
		super();
		this.cliente = cliente;
		this.fechaDeCreacion = LocalDate.now();
		this.movimientos = new ArrayList<Movimiento>();
		this.numeroDeCuenta = ULTIMO_NUMERO_CUENTA + 1;
		ULTIMO_NUMERO_CUENTA++;
		
	}
	
	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public List<Movimiento> getMovimientos() {
		//Me gustraria convertirlo en una lista de solo lectura
		return movimientos;
	}

	public double getSaldo() {
		//Se animan a corregirlo uds?
		double saldo = 0;
		
		for(Movimiento m : movimientos) {
            if(m.getDestino() == this) {
                saldo = saldo + m.getValor();
            } else {
                saldo = saldo - m.getValor();
            }
        }
		return saldo;
	}
	
	public abstract void depositar(OrigenDinero origen, double monto);
	
	public abstract void extraer(DestinoDinero destino, double monto) throws SaldoInsuficienteException;
	
}
