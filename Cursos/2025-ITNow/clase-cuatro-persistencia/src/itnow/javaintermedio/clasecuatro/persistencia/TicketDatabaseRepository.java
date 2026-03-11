package itnow.javaintermedio.clasecuatro.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import itnow.javaintermedio.clasecuatro.models.EstadoTicket;
import itnow.javaintermedio.clasecuatro.models.Ticket;

public class TicketDatabaseRepository implements Repository<Ticket> {
	
	private static final String CONNECTION_STRING = "jdbc:sqlite:itnow.db";
	
	public TicketDatabaseRepository() throws SQLException {
		//Que puedo hacer...
		
		//Opcion 1: try..finally
		/*Connection conn = DriverManager.getConnection(CONNECTION_STRING);
		try {
			...
		}finally {
			conn.close();
		}*/		
		
		//Opcion 2: try-with-resources
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = """
					CREATE TABLE IF NOT EXISTS tickets(
						id INTEGER PRIMARY KEY, 
						fecha TEXT NOT NULL,
						descripcion TEXT NOT NULL,
						estado TEXT NOT NULL
					);			
					""";
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		}
	}

	@Override
	public List<Ticket> getAll() throws SQLException {
		List<Ticket> result = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = "SELECT * from tickets";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Ticket ticket = new Ticket(
						rs.getInt("id"),
						LocalDateTime.parse(rs.getString("fecha")),
						rs.getString("descripcion"),
						EstadoTicket.valueOf(rs.getString("estado"))
						);
				result.add(ticket);
			}	
		}
		return result;
	}

	@Override
	public Ticket getById(int id) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = "SELECT * from tickets WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Ticket ticket = new Ticket(
						rs.getInt("id"),
						LocalDateTime.parse(rs.getString("fecha")),
						rs.getString("descripcion"),
						EstadoTicket.valueOf(rs.getString("estado"))
						);
				return ticket;
			}	
			return null;
		}
	}

	@Override
	public Ticket save(Ticket nuevo) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = "INSERT INTO tickets (fecha, descripcion, estado) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nuevo.getFechaDeCreacion().toString());
			stmt.setString(2, nuevo.getDescripcion());
			stmt.setString(3, nuevo.getEstado().toString());
			stmt.executeUpdate(); 
			
			//Actualizo el id generado en la base de datos
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				nuevo.asignarIdDesdeBase(keys.getInt(1));
			}

			return nuevo;
		}
	}

	@Override
	public Ticket update(Ticket existente) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = "UPDATE tickets SET fecha = ?, descripcion = ?, estado =?  WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, existente.getFechaDeCreacion().toString());
			stmt.setString(2, existente.getDescripcion());
			stmt.setString(3, existente.getEstado().toString());
			stmt.setInt(3, existente.getId());
			
			stmt.executeUpdate();

			return existente;
		}
	}

	@Override
	public boolean delete(int id) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
			String sql = "DELETE FROM tickets WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		}
	}

}
