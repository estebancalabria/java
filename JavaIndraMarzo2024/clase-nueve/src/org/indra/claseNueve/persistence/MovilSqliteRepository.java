package org.indra.claseNueve.persistence;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.indra.claseNueve.models.Movil;

import lombok.SneakyThrows;

public class MovilSqliteRepository extends SqliteRepository<Movil> {

	public MovilSqliteRepository() throws PersistenceException {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovilSqliteRepository(String fileName) throws PersistenceException {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getQueryCrearTabla() {
		// TODO Auto-generated method stub
		return """
                CREATE TABLE IF NOT EXISTS Movil (
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                Marca TEXT NOT NULL,
                Tamaño INTEGER NOT NULL)
            """;
	}

	@Override
	protected String getInsertQuery() {
		// TODO Auto-generated method stub
		return "INSERT INTO Movil (Marca, Tamaño) values (?,?)";
	}

	@Override
	protected String getSelect() {
		// TODO Auto-generated method stub
		return "SELECT * FROM Movil";
	}

	@Override
	protected Movil createModel() {
		// TODO Auto-generated method stub
		return new Movil();
	}

	@Override @SneakyThrows
	protected void setInsertParameters(Movil model, PreparedStatement insert) {
        insert.setString(1, model.getMarca());
        insert.setInt(2, model.getTamaño());
		
	}

	@Override @SneakyThrows
	protected void fillModelFromResultSet(Movil model, ResultSet result) {
        model.setId(result.getInt("id"));
        model.setMarca(result.getString("marca"));
        model.setTamaño(result.getInt("tamaño"));
		
	}

    /*private String CONNECTION_STRING = "jdbc:sqlite:demo.db";

    private void crearBaseDeDatos() throws PersistenceException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            Statement crearTabla = conn.createStatement();
            crearTabla.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Movil (
                    Id INTEGER PRIMARY KEY AUTOINCREMENT,
                    Marca TEXT NOT NULL,
                    Tamaño INTEGER NOT NULL)
                """);
        } catch (SQLException e) {
            throw new PersistenceException("No se pudo crear la tabla Movil", e);
        }       
    }

    public MovilSqliteRepository(String fileName) throws PersistenceException {
        this.CONNECTION_STRING = "jdbc:sqlite:" + fileName;
        this.crearBaseDeDatos();
    }
    
    public MovilSqliteRepository() throws PersistenceException {
        this.crearBaseDeDatos();
    }

    @Override
    public void save(Movil movil) throws PersistenceException {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            PreparedStatement insertarMovil = conn.prepareStatement("INSERT INTO Movil (Marca, Tamaño) values (?,?)");
            insertarMovil.setString(1, movil.getMarca());
            insertarMovil.setInt(2, movil.getTamaño());
            insertarMovil.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(MessageFormat.format("No se pudo insertar el móvil {0}", movil.getMarca()), e);
        }
    }

    @Override
    public void update(Movil movil) throws PersistenceException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void delete(Movil movil) throws PersistenceException {
        this.delete(movil.getId());
    }

    @Override
    public void delete(int id) throws PersistenceException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Movil findById(int id) throws PersistenceException {
        Movil movil = new Movil();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            PreparedStatement selectMovil = conn.prepareStatement("SELECT * FROM Movil WHERE id = ?");
            selectMovil.setInt(1, id);
            ResultSet resultSet = selectMovil.executeQuery();
            if (resultSet.next()) {
                movil.setId(resultSet.getInt("id"));
                movil.setMarca(resultSet.getString("marca"));
                movil.setTamaño(resultSet.getInt("tamaño"));
                System.out.println(movil);
            } else {
                throw new PersistenceException(MessageFormat.format("No existe el móvil con id {0}", id), null);              
            }
        } catch (SQLException e) {
            throw new PersistenceException(MessageFormat.format("No se pudo recuperar el móvil con id {0}", id), e);
        }
        return movil;
    }

    @Override
    public List<Movil> findAll() throws PersistenceException {
        List<Movil> moviles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            Statement selectMoviles = conn.createStatement();
            ResultSet resultSet = selectMoviles.executeQuery("SELECT * FROM Movil");
            while (resultSet.next()) {
                Movil movil = new Movil();
                movil.setId(resultSet.getInt("id"));
                movil.setMarca(resultSet.getString("marca"));
                movil.setTamaño(resultSet.getInt("tamaño"));
                moviles.add(movil);
            }
            return moviles;
        } catch (SQLException e) {
            throw new PersistenceException("Error al recuperar todos los móviles", e);
        }
    }*/
}
