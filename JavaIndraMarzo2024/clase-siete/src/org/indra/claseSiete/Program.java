package org.indra.claseSiete;

import java.sql.*;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:demo.db");*/
		
		//try-with-resources
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:demo.db")){
			System.out.println("Conexion con la base de datos exitosa.");
			System.out.println(conn.getClass());

			var crearTabla = conn.createStatement();
			int result = crearTabla.executeUpdate("""
					CREATE TABLE IF NOT EXISTS Cliente (
						Id INTEGER PRIMARY KEY AUTOINCREMENT,
						Nombre TEXT NOT NULL,
						Edad INTEGER NOT NULL
					)""");
			System.out.print("Tabla Cliente (Creada si no existe)");
			System.out.println(result); // 0 es que ya estaba creada

			var hayClientes = conn.createStatement();
			ResultSet resultTotalClientes = hayClientes.executeQuery("SELECT COUNT(*) FROM Cliente");
			int totalClientes = 0;
			if (resultTotalClientes.next()) {
				totalClientes = resultTotalClientes.getInt(1);
			}

			if (totalClientes == 0) {
				System.out.println("No hay Registros");
				System.out.println("Se van a insertar los clientes en la base de datos");
				var clientes = List.of(new Cliente("Jorge", 27), new Cliente("Alvaro", 24),
						new Cliente("Covadonga", 26), new Cliente("Ruben", 24), new Cliente("Jose", 24));

				for (Cliente c : clientes) {
					System.out.println("Insertando cliente " + c.getNombre());

					PreparedStatement insertarCliente = conn
							.prepareStatement("INSERT INTO Cliente (Nombre,Edad) VALUES (?,?)");
					insertarCliente.setString(1, c.getNombre());
					insertarCliente.setInt(2, c.getEdad());
					insertarCliente.executeUpdate();
				}
			} else {
				System.out.println("Ya hay registros. No se inserta nada");
			}

			System.out.println("Ahora voy a listar los Clientes");
			Statement selectCliente = conn.createStatement();
			ResultSet resultSet = selectCliente.executeQuery("Select * from Cliente");
			while (resultSet.next()) {
				String nombre = resultSet.getString("Nombre");
				int edad = resultSet.getInt("edad");
				System.out.println(nombre + " " + edad);
			}
			
		} catch (SQLException e) {
			System.err.println("Error al conectar a la base");
			System.err.println(e.getMessage());

		}
		
		/*} catch (SQLException e) {
			System.err.println("Error al conectar a la base");
			System.err.println(e.getMessage());
		}finally {
			if (conn!= null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		//Cliente c = new Cliente();
		Cliente c = Cliente.builder().nombre("Esteban").edad(43).build();
		System.out.println(c);
		
		
	}

}
