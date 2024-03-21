package org.indra.claseNueve.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.indra.claseNueve.models.BusinessObject;


//Template Method
public abstract class SqliteRepository<T extends BusinessObject> implements Repository<T> {
	private String CONNECTION_STRING = "jdbc:sqlite:demo.db";
	
	protected abstract String getQueryCrearTabla();
	protected abstract String getInsertQuery();
	protected abstract String getSelect();	
	protected abstract T createModel();
	protected abstract void setInsertParameters(T model, PreparedStatement insert);
	protected abstract void fillModelFromResultSet(T model, ResultSet result);
	
	
	//En realidad es crear Tabla
	private void crearBaseDeDatos() throws PersistenceException {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			// System.out.println("Conexion con la base de datos exitosa");
			// System.out.println(conn.getClass());

			Statement crearTabla = conn.createStatement();
			crearTabla.executeUpdate( this.getQueryCrearTabla() );

			// La voy a remplazar por un logger
			// System.out.println("Tabla cars (creada si no existe)");
			// System.out.println(result); //0 es que ya estaba creada
		} catch (SQLException e) {
			throw new PersistenceException(this.getClass().getName() +":No se pudo crear la tabla ", e);
		}		
	}

	public SqliteRepository(String fileName) throws PersistenceException {
		this.CONNECTION_STRING = "jdbc:sqlite:"+fileName;
		this.crearBaseDeDatos();
	}
	
	public SqliteRepository() throws PersistenceException {
		this.crearBaseDeDatos();
	}

	@Override
	public void save(T model) throws PersistenceException {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {

			PreparedStatement insert = conn.prepareStatement( this.getInsertQuery() );
			setInsertParameters(model, insert);
			insert.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(MessageFormat.format("No se pudo insertar el registro de {0}", model.getClass().getName()), e);
		}
	}

	@Override
	public void update(T model) throws PersistenceException {
		//throw new Error("Not Implemented");
        throw new UnsupportedOperationException("Not Implemented");
	}

	@Override
	public void delete(T model) throws PersistenceException {
		this.delete(model.getId());
	}

	@Override
	public void delete(int id) throws PersistenceException {
		//throw new Error("Not Implemented");
        throw new UnsupportedOperationException("Not Implemented");

	}

	@Override
	public T findById(int id) throws PersistenceException {
		//Car car = new Car();
		T model = this.createModel();
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			//PreparedStatement selectCar = conn.prepareStatement("SELECT * FROM Car WHERE id = ?");
			PreparedStatement select = conn.prepareStatement(this.getSelect() + " WHERE id = ?");
			select.setInt(1, id);
			ResultSet resultSet = select.executeQuery();
			// Iterar sobre los resultados
			if (resultSet.next()) {

				fillModelFromResultSet(model, resultSet);
				//car.setId(resultSet.getInt("id"));
				//car.setModel(resultSet.getString("model"));
				//car.setYear(resultSet.getInt("year"));
				//car.setColor(resultSet.getString("color"));

				//System.out.println(car);
			}
			else {
				throw new PersistenceException(MessageFormat.format("No existe el registro con id {0}", id), null);				
			}
		} catch (SQLException e) {
			throw new PersistenceException(MessageFormat.format("No se pudo recuperar el {0}", id), e);
		}
		return model;
	}

	@Override
	public List<T> findAll() throws PersistenceException {
        List<T> models = new ArrayList<T>();
        
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            
            System.out.println("Conexión con la base de datos existosa");
            System.out.println(conn.getClass());    
            
            Statement selectCliente = conn.createStatement();
            ResultSet resultSet = selectCliente.executeQuery(this.getSelect());
            
            while(resultSet.next()) {
            	/*Car car = new Car();
            	car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setColor(resultSet.getString("color"));
                cars.add(car);*/
            	T model = this.createModel();
            	fillModelFromResultSet(model, resultSet);
            	models.add(model);
            }
            
            return models;
            
        } catch (SQLException e) {
            throw new PersistenceException("No se pudo conectar con la base de datos",e);
        }    
	}


}
