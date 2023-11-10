package org.itnow.cursoSpring.claseUno.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.services.ClienteService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ClienteServivceTest {
	
	private ClienteService service;
	
	@BeforeEach
	void beforeAllTest() {
		@SuppressWarnings("resource")
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		this.service = (ClienteService)iocContainer.getBean("clienteService");
	}

	@Test
	void testDemoSumar() {
		int a = 2;
		int b = 2;
		assertEquals(4, a+b);
		//fail("Not yet implemented");
	}
	
	@Test
	void testAgregarCliente() {
		//ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		//ClienteService service = (ClienteService)iocContainer.getBean("clienteService");
		
		assertEquals(0, service.recuperarTodos().size() );
		Cliente c = new Cliente(1,"Juan"); 
		service.agregarCliente(c);
		
		assertEquals(1, service.recuperarTodos().size() );
	}

	
	@Test
	void testAgregarVariosCliente() {
		//ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		//ClienteService service = (ClienteService)iocContainer.getBean("clienteService");
		
		assertEquals(0, service.recuperarTodos().size() );
		service.agregarCliente(new Cliente(1,"Juan"));
		service.agregarCliente(new Cliente(2,"Alicia"));
		service.agregarCliente(new Cliente(3,"Bob"));
		service.agregarCliente(new Cliente(4,"Charles"));
		
		assertEquals(4, service.recuperarTodos().size() );
	}
}
