package org.allianz.clasecincospringboot.repository;

import java.util.*;

import org.allianz.clasecincospringboot.infraestructura.*;
import org.allianz.clasecincospringboot.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

//Pusimos constructor vacio
//inicializamos las variables 
//agregmos regenerateTable
//Pusimos el autowired y cambiamos el tipo al Log
//Comentamos el otro constructor

import lombok.SneakyThrows;


public class ClienteDatabaseRepository implements ClienteRepository {

	@Autowired
	private ConsoleLog log;
	private String connectionString = "jdbc:sqlite:base.db";
	private boolean regenerateTable = false;
	
	//public ClienteDatabaseRepository(Log log, String connectionString, boolean regenerateTable) throws Exception {
	public ClienteDatabaseRepository() throws Exception {
		//this.log = log;
		//this.connectionString = connectionString;
		
		this.log.info("Conectandose a la base con " + connectionString);
		Connection conn = DriverManager.getConnection(connectionString);
		try {
			if (regenerateTable) {
				Statement sql = conn.createStatement();
				sql.execute("DROP TABLE IF EXISTS Cliente");			
			}
			
			StringBuilder sb = new StringBuilder();
			
			
			sb.append("CREATE TABLE IF NOT EXISTS Cliente (");
			sb.append("   id INTEGER PRIMARY KEY AUTOINCREMENT,");
			sb.append("   documento INTEGER NOT NULL, ");
	        sb.append("   nombre TEXT NOT NULL,");
	        sb.append("   apellido TEXT NOT NULL");
			sb.append(");");
			
			this.log.info("Ejecutando SQL:");
			this.log.info(sb.toString());
			
			Statement sql = conn.createStatement();
			sql.execute(sb.toString());			
			
		} finally {
			conn.close();
		}
	}
	
	/*public ClienteDatabaseRepository(Log log, String connectionString) throws Exception {
		this(log, connectionString, false);
	}*/
	
	
	@Override
	@SneakyThrows
	public Cliente findById(int id){
		Connection conn = DriverManager.getConnection(this.connectionString);
		try {
			PreparedStatement sql = conn.prepareStatement("SELECT id,documento,nombre,apellido FROM Cliente WHERE id=?");
			sql.setInt(1, id);
			ResultSet tabla = sql.executeQuery();
			if (tabla.next()) {
				Cliente c = new Cliente();
				
				c.setId(tabla.getInt("id"));
				c.setDocumento(tabla.getInt("documento"));
				c.setNombre(tabla.getString("nombre"));
				c.setApellido(tabla.getString("apellido"));
				
				return c;
			}
			
			return null;
		} finally {
			conn.close();
		}
	}

	@Override
	@SneakyThrows
	public List<Cliente> findAll(){
		Connection conn = DriverManager.getConnection(connectionString);
		List<Cliente> clientes = new ArrayList<>();
		try {
			Statement sql = conn.createStatement();
			ResultSet tabla = sql.executeQuery("SELECT id,documento,nombre,apellido FROM Cliente");
			while (tabla.next()) {
				Cliente c = new Cliente();
				c.setId(tabla.getInt("id"));
				c.setDocumento(tabla.getInt("documento"));
				c.setNombre(tabla.getString("nombre"));
				c.setApellido(tabla.getString("apellido"));
				
				clientes.add(c);
			}
			return clientes;
		} finally {
			conn.close();
		}
	}

	@Override
	@SneakyThrows
	public void insert(Cliente nuevo) {
		Connection conn = DriverManager.getConnection(connectionString);
		try {
			PreparedStatement sql = conn.prepareStatement("INSERT INTO Cliente (documento, nombre, apellido) VALUES (?,?,?)");
			sql.setInt(1, nuevo.getDocumento());
			sql.setString(2, nuevo.getNombre());
			sql.setString(3, nuevo.getApellido());
			sql.execute();

			Statement sqlUltimoId = conn.createStatement();
			ResultSet result = sqlUltimoId.executeQuery("select last_insert_rowid()");
			if (result.next()) {
				nuevo.setId(result.getInt(1));
			}
		}finally {
			conn.close();
		}
	}

	@Override
	@SneakyThrows
	public void update(Cliente existente) {
		Connection conn = DriverManager.getConnection(connectionString);
		try {
            PreparedStatement sql = conn.prepareStatement("UPDATE Cliente SET nombre=?, apellido=?, documento=? WHERE id=?");
            sql.setString(1,existente.getNombre());
            sql.setString(2,existente.getApellido());
            sql.setInt(3,existente.getDocumento());
            sql.setInt(4,existente.getId());
            sql.execute();	
		}finally {
			conn.close();
		}
	}

	@Override
	@SneakyThrows
	public void delete(int id) {
		Connection conn = DriverManager.getConnection(connectionString);
		try {
            PreparedStatement sql = conn.prepareStatement("DELETE FROM CLIENTE WHERE ID = ?");
            sql.setInt(1,id);
            sql.execute();		
		}finally {
			conn.close();
		}
	}

}
