package org.indra.claseOcho.persistence;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;

import org.indra.claseOcho.models.*;

public class CarSqliteRepository implements Repository<Car> {

	private String CONNECTION_STRING = "jdbc:sqlite:demo.db";
	
	private void crearBaseDeDatos() throws PersistenceException {
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			// System.out.println("Conexion con la base de datos exitosa");
			// System.out.println(conn.getClass());

			Statement crearTabla = conn.createStatement();
			crearTabla.executeUpdate("""
					CREATE TABLE IF NOT EXISTS Car (
					    Id INTEGER PRIMARY KEY AUTOINCREMENT,
					    Model TEXT NOT NULL,
					    Year INTEGER NOT NULL,
					    Color TEXT NOT NULL)
					""");

			// La voy a remplazar por un logger
			// System.out.println("Tabla cars (creada si no existe)");
			// System.out.println(result); //0 es que ya estaba creada
		} catch (SQLException e) {
			throw new PersistenceException("No se pudo crear la tabla Car", e);
		}		
	}

	public CarSqliteRepository(String fileName) throws PersistenceException {
		this.CONNECTION_STRING = "jdbc:sqlite:"+fileName;
		this.crearBaseDeDatos();
	}
	
	public CarSqliteRepository() throws PersistenceException {
		this.crearBaseDeDatos();
	}

	@Override
	public void save(Car model) throws PersistenceException {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {

			PreparedStatement insertarCar = conn
					.prepareStatement("INSERT INTO Car (Model, Year, Color) values (?,?,?)");
			insertarCar.setString(1, model.getModel());
			insertarCar.setInt(2, model.getYear());
			insertarCar.setString(3, model.getColor());
			insertarCar.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(MessageFormat.format("No se pudo insertar el car {0}", model.getModel()), e);
		}

	}

	@Override
	public void update(Car model) throws PersistenceException {
		// TODO Auto-generated method stub
		throw new Error("Not Implemented");
	}

	@Override
	public void delete(Car model) throws PersistenceException {
		// TODO Auto-generated method stub
		this.delete(model.getId());
	}

	@Override
	public void delete(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		throw new Error("Not Implemented");
	}

	@Override
	public Car findById(int id) throws PersistenceException {
		Car car = new Car();
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			PreparedStatement insertarCar = conn.prepareStatement("SELECT * FROM Car WHERE id = ?");
			insertarCar.setInt(1, id);
			ResultSet resultSet = insertarCar.executeQuery();
			// Iterar sobre los resultados
			if (resultSet.next()) {

				car.setId(resultSet.getInt("id"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setColor(resultSet.getString("color"));

				System.out.println(car);
			}
			else {
				throw new PersistenceException(MessageFormat.format("No existe el car con id {0}", id), null);				
			}
		} catch (SQLException e) {
			throw new PersistenceException(MessageFormat.format("No se pudo recuperar el car {0}", car.getModel()), e);
		}
		return car;
	}

	@Override
	public List<Car> findAll() throws PersistenceException {
        List<Car> cars = new ArrayList<Car>();
        
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)){
            
            System.out.println("Conexión con la base de datos existosa");
            System.out.println(conn.getClass());    
            
            Statement selectCliente = conn.createStatement();
            ResultSet resultSet = selectCliente.executeQuery("Select * from Car");
            
            while(resultSet.next()) {
            	Car car = new Car();
            	car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setColor(resultSet.getString("color"));
                cars.add(car);
            }
            
            return cars;
            
        } catch (SQLException e) {
            throw new PersistenceException("No se pudo conectar con la base de datos",e);
        }    
	}

}
