package org.indra.persistence;

import java.sql.*;
import java.util.*;

import org.indra.model.Usuario;

public class UsuarioRepositorioDb implements IUsuarioRepositorio {

	@Override
	public Usuario buscarPorNombre(String nombre) {
		Connection conn = null;
		Usuario usr = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/indragram.db");
			Statement sql = conn.createStatement();
			ResultSet qry = sql.executeQuery(String.format("select * from usuario where nombre='%s';", nombre));
			if (qry.next()) {
				usr = new Usuario(qry.getString("Nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return usr;
	}

	@Override
	public List<Usuario> bucarTodos() {
		Connection conn = null;
		List<Usuario> result = new ArrayList<Usuario>();

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/indragram.db");
			Statement sql = conn.createStatement();
			ResultSet qry = sql.executeQuery("select * from usuario");
			while (qry.next()) {
				Usuario usr = new Usuario(qry.getString("Nombre"));
				result.add(usr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public void add(Usuario usuario) {
		Connection conn = null;
		if (usuario != null) {
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/indragram.db");
				Statement sql = conn.createStatement();
				sql.executeUpdate(String.format("insert into usuario (nombre) values ('%s');", usuario.getNombre()));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
