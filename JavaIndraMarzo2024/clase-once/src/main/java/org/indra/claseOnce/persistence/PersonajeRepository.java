package org.indra.claseOnce.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.indra.claseOnce.models.*;

import lombok.SneakyThrows;

public class PersonajeRepository extends SqliteRepository<Personaje> {

	@Override
	protected String getQueryCrearTabla() {
		return """
                CREATE TABLE IF NOT EXISTS Personaje (
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                Nombre TEXT NOT NULL)
            """;
	}

	@Override
	protected String getInsertQuery() {
		return  "INSERT INTO Personaje (Nombre) values (?)";
	}

	@Override
	@SneakyThrows
	protected void setInsertParameters(Personaje model, PreparedStatement insert) {
        insert.setString(1, model.getNombre());
	}

	@Override
	protected String getSelect() {
		// TODO Auto-generated method stub
		return "SELECT * FROM Personaje";	}

	@Override
	protected Personaje createModel() {
		// TODO Auto-generated method stub
		return new Personaje();
	}

	@Override
	@SneakyThrows
	protected void fillModelFromResultSet(Personaje model, ResultSet result) {
        model.setId(result.getInt("id"));
        model.setNombre(result.getString("nombre"));		
	}

}
