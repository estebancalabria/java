package org.indra.claseOnce.persistence;

import java.sql.*;
import java.text.MessageFormat;

import org.indra.claseOnce.models.ObjetoDeNegocio;

import lombok.SneakyThrows;

public abstract class SqliteRepository<T extends ObjetoDeNegocio> implements Repository<T> {

	private String CONNECTION_STRING = "jdbc:sqlite:demo.db";

	protected abstract String getQueryCrearTabla();
	protected abstract String getInsertQuery();
	protected abstract void setInsertParameters(T model, PreparedStatement insert);
	protected abstract String getSelect();	
	protected abstract T createModel();
	protected abstract void fillModelFromResultSet(T model, ResultSet result);


	@SneakyThrows
	private void crearBaseDeDatos() {
		Connection conn = DriverManager.getConnection(CONNECTION_STRING);
		Statement crearTabla = conn.createStatement();
		crearTabla.executeUpdate(this.getQueryCrearTabla());

	}

	@SneakyThrows
	public SqliteRepository(String fileName) {
		this.CONNECTION_STRING = "jdbc:sqlite:" + fileName;
		this.crearBaseDeDatos();
	}

	@SneakyThrows
	public SqliteRepository() {
		this.crearBaseDeDatos();
	}

	@SneakyThrows
	public void save(T obj) {
		Connection conn = DriverManager.getConnection(CONNECTION_STRING);
		PreparedStatement insert = conn.prepareStatement(this.getInsertQuery());
		setInsertParameters(obj, insert);
		insert.executeUpdate();
	}

	@SneakyThrows
	public T findById(int id) {
		T model = this.createModel();
		Connection conn = DriverManager.getConnection(CONNECTION_STRING);
		PreparedStatement select = conn.prepareStatement(this.getSelect() + " WHERE id = ?");
		select.setInt(1, id);
		ResultSet resultSet = select.executeQuery();
		if (resultSet.next()) {
			fillModelFromResultSet(model, resultSet);
		}
		else {
			throw new Exception(MessageFormat.format("No existe el registro con id {0}", id), null);				
		}

		return model;
	}

}
