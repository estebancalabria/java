package org.indra.claseOnce;

import org.indra.claseOnce.persistence.*;
import org.indra.claseOnce.services.*;
import org.indra.claseOnce.models.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos a Maven!");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		/*
		@SuppressWarnings("unchecked")
		Repository<Personaje> repo = (Repository<Personaje>)context.getBean("repositoryPersonaje");
		
		/*Personaje personaje = new Personaje();
		personaje.setNombre("Magneto");
		
		repo.save(personaje);*/
		/*Personaje personaje = repo.findById(1);
		System.out.println(personaje.getNombre());*/
		
		@SuppressWarnings("unchecked")
		PersonajeServiceInterface service = (PersonajeServiceInterface)context.getBean("servicePersonaje");
		service.registrarPersonaje("Xavier");
		
		Personaje p = service.recuperar(1);
		System.out.println(p.getNombre());
		
		
	}

}
