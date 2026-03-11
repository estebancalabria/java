package org.indra.main;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.indra.models.*;
import org.indra.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Cleanup;


public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos");
		
		//Configuracion de Hibernate
		//Mapeos con Archivos XML
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		//Mapeos con Anotaciones
		/*SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure()
				.addPackage("org.indra.models")
				.addAnnotatedClass(Libro.class)
				.buildSessionFactory();*/
		
		ServiceBase.configureSessionFactory(sessionFactory);
		
		//Configuracion de Spring
		@Cleanup ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		//ILibroService service = new LibroService();  //<<ESto lo vamos a reemplazar por la inyeccion de dependencias
		ILibroService service = (ILibroService)context.getBean("libroService");
		
		/*Libro pilares = new Libro();
		pilares.setTitulo("Los pilares de la tierra");
		pilares.setEscritor("Ken Follet");*/
		
		/*Libro libro = new Libro();
		libro.setTitulo("Juego de tronos");
		libro.setEscritor("George RR Martin");*/
		
		Libro libro = new Libro();
		libro.setTitulo("El Hobbit");
		libro.setEscritor("J.R.Tolkien");
		
		service.add(libro);
		
		
		System.out.println("Libro salvado en la base");
		
	}

}
