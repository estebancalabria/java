package indra.talentCamp.database;

import java.sql.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import indra.talentCamp.database.models.*;
import indra.talentCamp.database.services.PersonaService;

public class App {

	public static void main(String[] args) {
		System.out.println("JDBC es la hostia!");

		Connection conn = null;
		ApplicationContext iocContainer = null;
	
		try {
			try {
				iocContainer = new FileSystemXmlApplicationContext("applicationContext.xml");
				Persona daniel = (Persona)iocContainer.getBean("dany");
				System.out.println("Mi Bean creado por spring");
				System.out.println(daniel);
				
				System.out.println("Ahora sigamos con jdbc....");
				
				conn = DriverManager.getConnection("jdbc:sqlite:indra.db");
				
				System.out.println("Creando la base de datos indra.db...");
				
				Statement createStatement = conn.createStatement();
				createStatement.executeUpdate(
							"CREATE TABLE IF NOT EXISTS Persona(" +
							"ID INTEGER PRIMARY KEY AUTOINCREMENT," +
							"Nombre TEXT NOT NULL," +
							"Edad INTEGER NOT NULL)" 
						);
				
				System.out.println("Base de datos creada...");
				
				//Lo comento para no generar datos duplicados cada vez que ejecuto a aplicacion
				/*System.out.println("Insertando un par de personas");
				String theChosenOnes = "Angello,Jean,Marcos,Carlos";
				
				for (String chosenOne : theChosenOnes.split(",")) {
					System.out.println("Insertando a " + chosenOne);
					
					PreparedStatement insert = conn.prepareStatement("INSERT INTO Persona (Nombre, Edad) VALUES (?, ?)");	
					insert.setString(1, chosenOne);
					insert.setInt(2, 24);
					insert.executeUpdate();
				}
				
				System.out.println("Personas Insertadas...");*/
				
				System.out.println("Modificando la edad de Jean");
				PreparedStatement update = conn.prepareStatement("UPDATE Persona SET Edad=? WHERE Nombre=?");
				update.setInt(1, 23);
				update.setString(2, "Jean");
				update.executeUpdate();
				System.out.println("Edad Modificada...");
				
				/*Statement select = conn.createStatement();
				ResultSet resultSet  = select.executeQuery("SELECT * FROM Persona");
				List<Persona> cracksDeJava = new ArrayList<Persona>(); 
				while (resultSet.next()) {
					Persona crack = Persona
							.builder()
							.id(resultSet.getInt("id"))
							.nombre(resultSet.getString("nombre"))
							.edad(resultSet.getInt("edad"))
							.build();
					
					cracksDeJava.add(crack);
				}*/
				//Listo las personas con Spring
				
				PersonaService service = (PersonaService)iocContainer.getBean("personaService");
				List<Persona> cracksDeJava = service.cracksDeJava();
				cracksDeJava.forEach( System.out::println );
				
				
				
			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
