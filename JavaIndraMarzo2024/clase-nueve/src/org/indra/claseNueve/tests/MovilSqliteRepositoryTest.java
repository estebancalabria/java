package org.indra.claseNueve.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.indra.claseNueve.models.Movil;
import org.indra.claseNueve.persistence.MovilSqliteRepository;
import org.indra.claseNueve.persistence.PersistenceException;
import org.indra.claseNueve.persistence.Repository;

public class MovilSqliteRepositoryTest {
	
	private Repository<Movil> repo = null;
	private final String TEST_DB_NAME = "test.db";
	
	public MovilSqliteRepositoryTest() throws PersistenceException {
		new File(TEST_DB_NAME).delete();
		this.repo = new MovilSqliteRepository(TEST_DB_NAME);
	}

	@Test
	void testDatabaseExists() {
		assertTrue(new File(TEST_DB_NAME).exists());      
	}
	
	@Test
	void testSaveSuccess() throws PersistenceException {
	    // Crear un objeto Movil, hacer un save, recuperarlo (con id 1), verificar que los datos sean los mismos
		
        Movil movil = new Movil();
        movil.setId(1);
        movil.setMarca("Samsung");
        movil.setTamaño(6);
        repo.save(movil);
        
        Movil recuperado = repo.findById(1);
        
        assertEquals(1, recuperado.getId());
        assertEquals(movil.getMarca(), recuperado.getMarca());
        assertEquals(movil.getTamaño(), recuperado.getTamaño());
	}
}
