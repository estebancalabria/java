package org.indra.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;

import org.indra.models.Pelicula;
import org.indra.services.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class PeliculaServiceTest {
	
	//Reemplazo la consola por una consola alternativa en memoria
	private static PrintStream outConsola = System.out;
	private static ByteArrayOutputStream outAlternativo = new ByteArrayOutputStream();
	ApplicationContext context;
	
	public PeliculaServiceTest(){
		this.context = new ClassPathXmlApplicationContext("beans.test.xml");
	}

	@Test
	@Order(0)
	void testPeliculaService() {
		System.setOut(new PrintStream(outAlternativo));
		IPeliculaService service = (IPeliculaService)this.context.getBean("peliculaService");
		Pelicula pelicula = new Pelicula() {{
			setTitulo("Super 8");
			setFecha(LocalDate.of(2011, Month.JULY, 1));
		}};
		service.add(pelicula);
		assertEquals("Servicio Alternativo", outAlternativo.toString().trim());
		System.setOut(outConsola);
	}
	
	@Test
	void testPeliculaServiceIsSingleton() {
		IPeliculaService service = (IPeliculaService)this.context.getBean("peliculaService");
		IPeliculaService service2 = (IPeliculaService)this.context.getBean("peliculaService");
        assertEquals(service, service2);
        assertEquals(true, this.context.isSingleton("peliculaService"));
	}

	@Test
	void testNotSigletonBean() {
		ApplicationContext altContext = new ClassPathXmlApplicationContext("beans.notsingleton.test.xml");
		IPeliculaService service = (IPeliculaService)altContext.getBean("peliculaService");
		IPeliculaService service2 = (IPeliculaService)altContext.getBean("peliculaService");
		assertEquals(true, altContext.isPrototype("peliculaService"));
        assertNotEquals(service, service2);	
	}
	
	@Test
	void testWithMemoryRepository(){
		ApplicationContext altContext = new ClassPathXmlApplicationContext("beans.memoryRepository.test.xml");
		IPeliculaService service = (IPeliculaService)altContext.getBean("peliculaService");
		
		Pelicula pelicula = new Pelicula() {{
			setTitulo("Titanic");
			setFecha(LocalDate.of(1997, Month.JULY, 1));
		}};
		service.add(pelicula);

		Pelicula recuperada = service.findById(1);
	    assertEquals(pelicula.getTitulo(), recuperada.getTitulo());
	    assertEquals(pelicula.getFecha(), recuperada.getFecha());
	}
}
