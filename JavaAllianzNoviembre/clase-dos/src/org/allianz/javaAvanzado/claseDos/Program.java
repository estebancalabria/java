package org.allianz.javaAvanzado.claseDos;

import java.sql.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola Clase Dos");
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:baseDeDatos.db");
			Statement sql = conn.createStatement();
			sql.execute("CREATE TABLE IF NOT EXISTS TEMP (NUMERO integer NOT NULL);");
			sql.execute("INSERT INTO TEMP (NUMERO) VALUES (1)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
