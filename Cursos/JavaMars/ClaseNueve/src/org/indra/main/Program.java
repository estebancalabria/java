package org.indra.main;

import java.time.LocalDate;
import java.time.Month;
import org.indra.models.Pelicula;
import org.indra.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		//Ejemplo 1 : Sin Spring
		/*PeliculaService service = new PeliculaService(); //<<< Esto lo podriamos crear con un Factory
		
		Pelicula pelicula = new Pelicula() {{
			setTitulo("Super 8");
			setFecha(LocalDate.of(2011, Month.JULY, 1));
		}};
		
		service.add(pelicula);*/
		
		//Ejemplo 2 : Con Spring
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		IPeliculaService service = (IPeliculaService)context.getBean("peliculaService");
		
		Pelicula pelicula = new Pelicula() {{
			setTitulo("Super 8");
			setFecha(LocalDate.of(2011, Month.JULY, 1));
		}};
		
		service.add(pelicula);

		((ConfigurableApplicationContext)context).close();
	}

}
