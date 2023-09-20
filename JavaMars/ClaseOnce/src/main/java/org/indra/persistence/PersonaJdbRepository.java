package org.indra.persistence;

import java.sql.*;

import org.indra.models.Persona;

public class PersonaJdbRepository implements IPersonaRepository{

	public void add(Persona persona) throws Exception {
		
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:persona.db")){
			Statement sql = connection.createStatement();
			sql.executeUpdate(String.format("INSERT INTO PERSONA (Nombre,Apellido) VALUES ('%s','%s')", 
					persona.getNombre(), persona.getApellido()));
		} 
		
	}

}
