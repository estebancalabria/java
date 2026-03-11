package org.indra.claseOcho.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.indra.claseOcho.models.*;
import org.indra.claseOcho.persistence.*;


public class CarSqliteRepositoryTest {
	
	private Repository<Car> repo = null;
	private final String TEST_DB_NAME= "test.db";
	
	public CarSqliteRepositoryTest() throws PersistenceException{
		new File(TEST_DB_NAME).delete();
		this.repo = new CarSqliteRepository(TEST_DB_NAME);
	}

	/*@Before
	public void setUp() throws PersistenceException {
		//Quiero crear el repositorio y borrar el archivo si existe
		this.repo = new CarSqliteRepository(TEST_DB_NAME);
	}
	
	
	@After
	public void tearDown() {
		new File(TEST_DB_NAME).delete();
	}*/ //No me funcionan grrrrrrrrrrr
	

	@Test
	void testDatabaseExists() {
		assertTrue(new File(TEST_DB_NAME).exists());      
	}
	
	@Test
	void testSaveSuccess() throws PersistenceException {
	    //Se animan a programarlo?
		//Crear un objeto, hacer un save, recuperarlo (con id 1), verificar que los datos sean los mismos
		
        Car car = new Car();
        car.setId(1);
        car.setModel("audi");
        car.setColor("negro");
        car.setYear(2020);
        repo.save(car);
        
        Car recuperado = repo.findById(1);
        
        assertEquals(1,recuperado.getId());
        assertEquals(car.getModel(),recuperado.getModel());
        assertEquals(car.getColor(),recuperado.getColor());
        assertEquals(car.getYear(),recuperado.getYear());
	}
}
