package indra.talentCamp.database.persistence;

import java.sql.*;
import java.util.*;

import indra.talentCamp.database.models.Persona;
import lombok.*;

public class PersonaRepository implements Repository<Persona> {
	
	@Getter @Setter 
	private String connectionString;

	@Override
	public void save(Persona nuevo) throws SaveErrorException {
		// TODO Auto-generated method stub
		throw new Error("Not Implemented Yet");
	}

	@Override
	public void update(Persona existente) throws UpdateErrorException {
		// TODO Auto-generated method stub
		throw new Error("Not Implemented Yet");
	}

	@Override
	public void delete(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		throw new Error("Not Implemented Yet");
	}

	@Override
	public List<Persona> findAll() {
		// Me programan ustedes el find all?
		List<Persona> result = new ArrayList<Persona>();
		Connection conn = null;
		try {
			try {
				//conn = DriverManager.getConnection("jdbc:sqlite:indra.db");
				conn = DriverManager.getConnection(this.connectionString);

				Statement select = conn.createStatement();
				ResultSet resultSet = select.executeQuery("SELECT * FROM Persona");
				while (resultSet.next()) {

					Persona pe = new Persona();
					pe.setId(resultSet.getInt("id"));
					pe.setNombre(resultSet.getString("nombre"));
					pe.setEdad(resultSet.getInt("edad"));

					result.add(pe);
				}

			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return result;
	}

	@Override
	public Persona findById(int id) throws NotFoundException {
		throw new Error("Not Implemented Yet");
		// TODO Auto-generated method stub
		// return null;
	}

}
