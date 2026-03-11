package org.allianz.javaAvanzado.claseUno.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.allianz.javaAvanzado.claseUno.infraestructura.*;
import org.allianz.javaAvanzado.claseUno.repository.*;
import org.allianz.javaAvanzado.claseUno.models.*;
import org.junit.jupiter.api.Test;

class ClienteRepositoryTest {

	@Test
	void testSumar() {
		int a = 2;
		int b = 2;
		assertEquals(4, a+b);
	}
	
	@Test
	void testFindByAll() {
		
		ClienteRepository repo = new ClienteMemoryRepository(new DummyLog()); 
		List<Cliente> clientes = repo.findAll();
		
	    assertEquals(true, clientes.isEmpty());
	    
		Cliente jeff = new Cliente(123, "Jeff", "Bezos");
		repo.insert(jeff);
		clientes = repo.findAll();
		
		assertEquals(1, clientes.size());
	}
	
	@Test
	void testFindById() {
	   ClienteRepository repo = new ClienteMemoryRepository(new DummyLog());
	   Cliente jeff = new Cliente(123, "Jeff", "Bezos");
	   repo.insert(jeff);
	   
	   Cliente copiaDeJeff = repo.findById(jeff.getId());
	   
	   assertEquals(jeff.getId(), copiaDeJeff.getId());
	}
	
	@Test
	void testDelete() {
		   ClienteRepository repo = new ClienteMemoryRepository(new DummyLog());
		   Cliente jeff = new Cliente(123, "Jeff", "Bezos");
		   
		   assertEquals(true, repo.findAll().isEmpty());
		   
		   repo.insert(jeff);
		   
		   assertEquals(false, repo.findAll().isEmpty());
		   
		   repo.delete(jeff.getId());
		   
		   assertEquals(true, repo.findAll().isEmpty());
	}

}
