package itnow.javaintermedio.clasecuatro;

import java.sql.*;
import java.util.UUID;

public class Programa {

	public static void main(String[] args) throws SQLException {
		System.out.println("Bienvenidos a la clase 4");
		System.out.println("Introduccion a JDBC (Java Satabase connectivity)");
		System.out.println("------------------------------------------------");
		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:itnow.db");
		try {
			System.out.println("Conectado a base itnow.db de forma satisfactoria");
			Programa.crearTablaAlumnos(conn);
			Programa.insertarAlumnoDePrueba(conn);
			Programa.mostarAlumnos(conn);
			
		} finally {
			conn.close();
		}
	}
	
	public static void crearTablaAlumnos(Connection conn) {
		String sql = """
				CREATE TABLE IF NOT EXISTS alumnos(
					id INTEGER PRIMARY KEY, 
					nombre TEXT NOT NULL
				);			
				""";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Tabla alumnos creada Satisfactoriamente...");
		} catch (SQLException ex) {
			System.out.println("Error al crear la tabla : " + ex.getMessage());
		}
	}
	
	private static String getNombreAleatorio() {
		String nombreRandom = UUID.randomUUID().toString();
		return nombreRandom;
	}
	
	private static void insertarAlumnoDePrueba(Connection conn) {
		try {
			String sql = "INSERT INTO alumnos (nombre) VALUES (?)";
			String nombreNuevo = Programa.getNombreAleatorio();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, nombreNuevo);
			stmt.executeUpdate();
			System.out.println("Insertado en forma satisfactoria a alumno " + nombreNuevo);
			
		}catch (SQLException ex) {
			System.out.println("Error al insertar el registro : " + ex.getMessage());
		}
	}
	
	private static void mostarAlumnos(Connection conn) {
		try {
			String sql = "SELECT * from alumnos";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Alumno : "+ rs.getInt("id") +"-" + rs.getString("nombre"));
			}	
		}catch (SQLException ex) {
			System.out.println("Error al insertar el registro : " + ex.getMessage());
		}
	}

}
