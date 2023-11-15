package org.allianz.javaAvanzado.claseUno.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.allianz.javaAvanzado.claseUno.repository.*;
import org.allianz.javaAvanzado.claseUno.infraestructura.*;
import org.allianz.javaAvanzado.claseUno.models.Cliente;
import org.junit.jupiter.api.Test;

class ClienteDatabaseRepositoryTest {

	private ClienteDatabaseRepository repo;

	ClienteDatabaseRepositoryTest() throws Exception {
		this.repo = new ClienteDatabaseRepository(new DummyLog(), "jdbc:sqlite:tests.db", true);
	}

	@Test
	void testInsert() {
		Cliente c = new Cliente(123, "Tony", "Stark");
		repo.insert(c);

		Cliente recuperado = repo.findById(c.getId());
		assertEquals(123, recuperado.getDocumento());
		assertEquals("Tony", recuperado.getNombre());
		assertEquals("Stark", recuperado.getApellido());
	}

	@Test
	void testUpdate() {
		// Cliente c = new Cliente(2, "Esteban","Strange");
		Cliente c = Cliente.builder().documento(2).nombre("Esteban").apellido("Strange").build();

		repo.insert(c);

		c.setNombre("Stephen");
		repo.update(c);

		Cliente recuperado = repo.findById(c.getId());
		assertEquals(2, recuperado.getDocumento());
		assertEquals("Stephen", recuperado.getNombre());
		assertEquals("Strange", recuperado.getApellido());
	}

	@Test
	void testDelete() {
		Cliente c = new Cliente(400, "Peter", "Parker");
		repo.insert(c);

		List<Cliente> clientes = repo.findAll();
		int countAntes = clientes.size();

		repo.delete(c.getId());
		clientes = repo.findAll();
		int countDespues = clientes.size();

		assertEquals(countAntes - 1, countDespues);
	}

}
