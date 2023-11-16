package org.allianz.javaAvanzado.claseCuatro;

import java.util.*;
import java.io.*;

import org.allianz.javaAvanzado.claseCuatro.controllers.*;
import org.allianz.javaAvanzado.claseCuatro.infraestructura.*;
import org.allianz.javaAvanzado.claseCuatro.models.*;
import org.allianz.javaAvanzado.claseCuatro.repository.*;
import org.allianz.javaAvanzado.claseCuatro.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.*;


public class Programa {

	public static void main(String[] args) throws Exception  {
        System.out.println("Clase 4");
        
        /*Log log = new ConsoleLog();
        //Log log = new FileLog();
        
        /*List<Cliente> clientes = new ArrayList<>();
        
        //Ejemplo 1 - Creo una clase del Modelo
        Cliente elon = new Cliente();
        elon.setDocumento(1);
        elon.setNombre("Elon");
        elon.setApellido("Musk");
        clientes.add(elon);
        
        Cliente mark = new Cliente(2, "Mark", "Zuckemberg");
        clientes.add(mark);
        
        //Ya no uso mas la consola, ahora voy a usar el log
        //clientes.forEach( c -> { System.out.println(c); });
        //clientes.forEach( System.out::println );
        clientes.forEach( c -> { log.info(c.toString()); });*/
        /*Log log = new ConsoleLog();
        try {
			ClienteRepository repo = new ClienteDatabaseRepository(log, "jdbc:sqlite:clientes.db");
			
			//Test 1: El insert y findById
			/*Cliente c = new Cliente(123,"Tony", "Stark");
			repo.insert(c);
			
			Cliente recuperado = repo.findById(c.getId());
			System.out.println(recuperado);*/
			
			//Test 2: el findAll
			/*List<Cliente> clientes = repo.findAll();
			//Forma 1
			clientes.forEach(System.out::println);
			//Forma 2
			clientes.forEach(c -> { System.out.println(c); } );
			//Forma 3
			for (Cliente c : clientes) {
				System.out.println(c);
			}*/
			
			//Test 3: El update
			/*Cliente c = new Cliente(2, "Esteban","Strange");
			repo.insert(c);
			System.out.println(c);
			c.setNombre("Stephen");
			repo.update(c);
			System.out.println(c);*/
			
			//Test 4: El Delete
			/*Cliente c = new Cliente(400, "Peter","Parker");
			repo.insert(c);
			List<Cliente> clientes = repo.findAll();
			System.out.println("Antes del Delete");
			clientes.forEach(System.out::println);
			
			repo.delete(c.getId());
			
			clientes = repo.findAll();
			System.out.println("Despues del Delete");
			clientes.forEach(System.out::println);*/

		/*} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        //Tiempo de Configuracion
        /*ClienteRepository repository = new ClienteDatabaseRepository(new ConsoleLog(),"jdbc:sqlite:clientes.db");
        ClienteService service = new ClienteServiceImplementation(repository);
        ClienteController controller = new ClienteController(service);
        
        //Tiempo de Ejecucion (propiamente dicho)
        //System.out.println(controller.get());
        FileWriter writer = new FileWriter("html/clientes.html");
        try {
        	writer.write(controller.get());
        } finally {
        	writer.close();
        }*/
        
        //Spring
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        try {
        	//Creamos Log
	        //Log log = (Log)context.getBean("log");
	        //log.info("Mi Primer Ejemplo de Spring");
        	
        	//Creamos repository
        	/*ClienteRepository repo = (ClienteRepository)context.getBean("clienteRepository");
        	repo.insert(new Cliente(1234,"Scott","Adams"));
        	repo.findAll().forEach(System.out::println);*/
        	
        	//Ahora le toca al service
        	//ClienteService service = (ClienteService)context.getBean("clienteService");
        	//service.obtenerTodos().forEach(System.out::println);
        	
        	//Por ultimo el controller
            ClienteController controller = (ClienteController)context.getBean("clienteController");
            System.out.println(controller.get());
        } finally {
        	context.close();
        }
        
	}

}

