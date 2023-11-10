package org.itnow.cursoSpring.claseUno.repository;

import java.sql.*;
import java.util.*;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.utils.Logger;

public class ClienteDatabaseRepository implements ClienteRepository {

	private Logger logger;

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private String driver = "jdbc:sqlite:cliente.db";
	private Connection conn = null;

	public ClienteDatabaseRepository(Logger logger) {
		this.logger = logger;
		// this.jdbcdriver = jdbcdriver;

		try {
			this.conn = DriverManager.getConnection(this.driver);
			Statement sql = conn.createStatement();
			sql.execute("""
							CREATE TABLE IF NOT EXISTS Cliente (
								id integer PRIMARY KEY,
								documento integer NOT NULL,
								nombre text NOT NULL
							);
					""");
		} catch (Exception ex) {
			logger.log("ERROR AL CONECTARNOS CON LA BD" + ex.getMessage());
			throw new Error(ex.getMessage());
		}
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Override
	public void insert(Cliente cliente) {
		this.logger.log("Inserto un cliente en la base dedatos");
		try {
			java.sql.Statement sqlInsert = conn.createStatement();
			// Ojo con inyeccion de SQL
			sqlInsert.execute(String.format("""
						INSERT INTO Cliente(documento,nombre)
						values (%d,%s);
					;""", cliente.getDocumento(), cliente.getNombre()));
		} catch (Exception e) {
			this.logger.log("Error al insertar " + e.getMessage());
			throw new Error(e.getMessage());
		}
		this.logger.log("Inserto un cliente en la BBDD");
	}

	@Override
	public List<Cliente> findAll() {
		try {
			// Se animan a hacerlo ustedes?
			Statement sqlSelect = this.conn.createStatement();
			ResultSet qry = sqlSelect.executeQuery("select * from Cliente;");
			while (qry.next()) {
				Cliente c = new Cliente(qry.getInt("documento"), qry.getString("nombre"));
				this.clientes.add(c);
			}

			this.logger.log("Recupero todos los clientes de la base de datos");
			return this.clientes;
		} catch (Exception ex) {
			this.logger.log("ERROR AL HACER SELECT");
			throw new Error(ex.getMessage());
		}
	}
	
	private void destruirBean() {
		logger.log("Destruyendo el BEAN Cliente Database Repository");
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.log("Error al destruir el bean");
			}
		}
	}

}
