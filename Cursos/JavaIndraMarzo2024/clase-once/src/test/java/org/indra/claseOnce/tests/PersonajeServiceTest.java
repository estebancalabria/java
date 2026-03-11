package org.indra.claseOnce.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.indra.claseOnce.models.Personaje;
import org.indra.claseOnce.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class PersonajeServiceTest {

	@Test
	void testPersonajeService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		@SuppressWarnings("unchecked")
		PersonajeServiceInterface service = (PersonajeServiceInterface)context.getBean("servicePersonaje");
		service.registrarPersonaje("Xavier");
		
		Personaje p = service.recuperar(1);
		assertEquals("Xavier", p.getNombre());
	}
	
	@Test
	void testPersonajeService_ConstructorDependencyInjection() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		@SuppressWarnings("unchecked")
		PersonajeServiceInterface service = (PersonajeServiceInterface)context.getBean("servicePersonajeConstructor");
		service.registrarPersonaje("Xavier");
		
		Personaje p = service.recuperar(1);
		assertEquals("Xavier", p.getNombre());
	}


}
