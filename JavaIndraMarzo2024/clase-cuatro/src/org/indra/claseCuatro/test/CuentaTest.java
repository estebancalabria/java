package org.indra.claseCuatro.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.indra.claseCuatro.models.*;
import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	void testDepositarCajaDeAhorro() {
        Cliente cl = new Cliente(1234, "Alvaro", "Orejon", LocalDate.of(1975, 5, 2) );
        CajaDeAhorros caja = new CajaDeAhorros(cl);
        caja.depositar(OrigenDineroConocido.cajero(), 100);
        assertEquals(100, caja.getSaldo());
	}
	
	@Test
	void testDepositarValorNegativoCajaDeAhorro() {
        assertThrows(Error.class, ()->{
            Cuenta c = new CajaDeAhorros(new Cliente(1234, "Juan", "Perez",LocalDate.of(1975, 5, 2)));
            c.depositar(OrigenDineroConocido.cajero(), -1000);
        });
	}
	
	@Test
	void testDepositarExtraerAhorroSaldoSuficiente() throws SaldoInsuficienteException {
        Cuenta c = new CajaDeAhorros(new Cliente(1231421, "Pedro", "Ejemplo", LocalDate.of(1986, 12, 31)));
        c.depositar(OrigenDineroConocido.cajero(), 2000);
        c.extraer(DestinoDineroConocido.cajero(), 1000);
        assertEquals(1000, c.getSaldo());	
    }
	
	@Test
	void testDepositarExtraerAhorroSaldoInsuficiente() {
		assertThrows(SaldoInsuficienteException.class, ()->{
	        Cuenta c = new CajaDeAhorros(new Cliente(1231421, "Pedro", "Ejemplo", LocalDate.of(1986, 12, 31)));
	        c.extraer(DestinoDineroConocido.cajero(), 1000);		
		});
	}


}
