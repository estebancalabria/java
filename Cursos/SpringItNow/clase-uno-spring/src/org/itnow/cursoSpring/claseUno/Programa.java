package org.itnow.cursoSpring.claseUno;

import java.sql.*;
import org.sqlite.*;
import org.itnow.cursoSpring.claseUno.controllers.ClienteController;
import org.itnow.cursoSpring.claseUno.models.*;
import org.itnow.cursoSpring.claseUno.repository.*;
import org.itnow.cursoSpring.claseUno.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 

public class Programa {

	public static void main(String[] args) {

		// Ejemplo 1 - Con Pojos (sin Beans)
		// DemoBean demo = new DemoBean();
		// demo.setTexto("Hola Mundo Sin Spring (con pojos)");
		// System.out.println(demo.getTexto());

		// Ejemplo 2 - Hello World con Spring
		// ApplicationContext iocContainer = new
		// ClassPathXmlApplicationContext("SpringConfig.xml");
		// DemoBean demo = (DemoBean)iocContainer.getBean("demoBean");
		// System.out.println(demo.getTexto());

		// Ejemplo 3 - Arquitectura de Referencia Sin Spring
		/*
		 * Cliente c = new Cliente(1,"Juan"); ClienteService service = new
		 * ClienteService(); service.agregarCliente(c);
		 */

		// Ejemplo 4 - Inyeccion de dependencia por constructor
		/*
		 * Cliente c = new Cliente(1,"Juan"); //ClienteDatabaseRepository repository =
		 * new ClienteDatabaseRepository(); ClienteMockRepository repository = new
		 * ClienteMockRepository(); ClienteService service = new
		 * ClienteService(repository); service.agregarCliente(c);
		 */

		// Ejemplo 5 = Inyeccion de dependendecias por setter
		/*Cliente c = new Cliente(1, "Juan");
		ClienteDatabaseRepository repository = new ClienteDatabaseRepository();
		// ClienteMockRepository repository = new ClienteMockRepository();
		ClienteService service = new ClienteService();
		service.setRepository(repository); // <<<< Setter Inyection
		service.agregarCliente(c);*/
		
		//Ejemplo 6 : Inyeccion de dependencia por Constructor con Spring
		//ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringConfig.xml");
		//ClienteService service = (ClienteService)iocContainer.getBean("clienteService");
		//Cliente c = new Cliente(1,"Juan"); 
		//service.agregarCliente(c);
		
		//Ejemplo 7 : Controladores
		//ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringConfig.xml");
		//ClienteController controller = (ClienteController)iocContainer.getBean("clienteController");
		//System.out.println("Simulo un HTTP GET a /clientes");
		//System.out.println(controller.index());
		
		//Ejemplo 8 : Ejemplo aislado jdbc
		/*Connection conn = null;
		try {
			try {
				//Para Postgres
				//String jdbcURL = "jdbc:postgresql://192.168.1.170:5432/sample";
				//Connection connection = DriverManager.getConnection(jdbcURL, "user", "password");
				
			
				conn = DriverManager.getConnection("jdbc:sqlite:cliente.db");
				Statement sql = conn.createStatement();
				sql.execute("""
						CREATE TABLE IF NOT EXISTS Cliente (
							id integer PRIMARY KEY,
							documento integer NOT NULL,
							nombre text NOT NULL
						);
				""");
				
				//En PostgreSQL
				/*sql.execute("""
						CREATE TABLE IF NOT EXISTS Cliente (
							id serial PRIMARY KEY,
							documento integer NOT NULL,
							nombre text NOT NULL
						);
				""");*/
				
			/*	Statement sqlInsert = conn.createStatement();
				sqlInsert.execute(""" 
						INSERT INTO Cliente
						(documento, nombre)
						values
						(1,'Juan');
				""");
				
				Statement sqlSelect = conn.createStatement();
				ResultSet qry = sqlSelect.executeQuery("select * from Cliente;");
				while (qry.next()) {
					System.out.println(String.format("%d %d %s",
							qry.getInt("id"),
							qry.getInt("documento"),
							qry.getString("nombre")));
				}
				
				
				
			} catch (Exception ex) {
				System.out.println("Error " + ex.getMessage());
			}
		
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}*/
		
		//Ejemplo 8 : Lo mismo que el 7 pero ahora vamos con el repositorio que lee de la base de datos
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringConfig.xml");
		try {
			ClienteController controller = (ClienteController)iocContainer.getBean("clienteController");
			System.out.println("Simulo un HTTP GET a /clientes");
			System.out.println(controller.index());
		} finally {
			((ClassPathXmlApplicationContext)iocContainer).close();
		}
		
	}
}
