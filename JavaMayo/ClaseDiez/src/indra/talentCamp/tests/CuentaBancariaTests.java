package indra.talentCamp.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import indra.talentCamp.banco.*;

class CuentaBancariaTests {
	
	Cliente demoCliente;
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("Antes de ejecutar todos los tests");
	}
	
	@BeforeEach
	public void before() {
		System.out.println("Antes de Ejecutar un unit test");
		
		demoCliente = Cliente.builder()
	    		.setIdentificador(123)
	    		.setNombre("Esteban")
	    		.setApellido("Calabria")
	    		.build();
	}

	@Test
	void ShouldAutoincrementCuentaBancariaNumero() {
		
	    Banco banco = new Banco();
		
		//Cliente demoCliente = new Cliente(123,"Esteban","Calabria");	    
	    /*Cliente demoCliente = Cliente.builder()
	    		.setIdentificador(123)
	    		.setNombre("Esteban")
	    		.setApellido("Calabrua")
	    		.build();*/
	    
		//No la puedo crear directo porque el constructor es package-private
		CuentaBancaria cuentaUno = banco.crearCuentaCorriente(demoCliente);
		CuentaBancaria cuentaDos = banco.crearCajaDeAhorrro(demoCliente);
		
		assertTrue(cuentaUno.getNumeroCuenta() < cuentaDos.getNumeroCuenta(), "Las dos cuentas no son correlativas");
		assertEquals(1, cuentaUno.getNumeroCuenta());
		assertEquals(2, cuentaDos.getNumeroCuenta(), "La cuenta dos no tien numero dos");
	}
	
	@Test
	void ShouldNotBeAbleToExtractMoreFromCajaDeAhorro() {
		Banco banco = new Banco();
	    
		/*Cliente demoCliente = Cliente.builder()
	    		.setIdentificador(123)
	    		.setNombre("Esteban")
	    		.setApellido("Calabrua")
	    		.build();*/
	    
		CuentaBancaria cajaDeAhorro = banco.crearCajaDeAhorrro(demoCliente);
		cajaDeAhorro.depositar(100);
		
		assertTrue(cajaDeAhorro.getSaldo()>0);
		assertThrows(SaldoInsuficienteException.class, ()-> cajaDeAhorro.extraer(200));
	}
	
	@Test 
	void ShouldExtractCorrectly() throws SaldoInsuficienteException{
		Banco banco = new Banco();
		
	    /*Cliente demoCliente = Cliente.builder()
	    		.setIdentificador(123)
	    		.setNombre("Esteban")
	    		.setApellido("Calabrua")
	    		.build();*/
		
		CuentaBancaria cajaDeAhorro = banco.crearCajaDeAhorrro(demoCliente);
		cajaDeAhorro.depositar(100);
		cajaDeAhorro.extraer(50);
		
		assertEquals(50, cajaDeAhorro.getSaldo());                                                             
	}
	
	
	
}
