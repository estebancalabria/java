package org.allianz.hellospringboot;

import org.allianz.hellospringboot.models.Cliente;
import org.allianz.hellospringboot.repositories.ClienteRepository;
import org.allianz.hellospringboot.services.IClienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ClienteServiceTests {

	@MockBean
	private ClienteRepository repository;
	
	@Autowired
	private IClienteService service;
	
	@BeforeEach
	public void configurarMocks() {
		
		Cliente clienteSimulado = new Cliente();
		clienteSimulado.setId(1);
		clienteSimulado.setNombre("TEST");
		clienteSimulado.setApellido("TEST");
		clienteSimulado.setTelefono("555-555555");
		clienteSimulado.setEmail("test@test");

		List<Cliente> baseSimulada = new ArrayList<Cliente>();
		baseSimulada.add(clienteSimulado);
		
		when(repository.findAll()).thenReturn(baseSimulada);
		when(repository.findById(1)).thenReturn(Optional.of(clienteSimulado));
		when(repository.findById(2)).thenReturn(Optional.empty());
	}
	
	@Test
	public void testGetAll() {
		List<Cliente> all = this.service.getAll();		
		assertThat(all.size()).isGreaterThanOrEqualTo(1);
		assertThat(all.get(0).getNombre()).isEqualTo("TEST");
	}
	
	@Test 
	public void testGetById_Existing() {
		Cliente c = this.service.getById(1);
		assertThat(c.getNombre()).isEqualTo("TEST");
		assertThat(c.getApellido()).isEqualTo("TEST");
		assertThat(c.getEmail()).isEqualTo("test@test");
	}
	
	@Test
	public void testGetById_NotExisting() {
		assertThrows(RuntimeException.class, ()->{
			this.service.getById(2);
		});
	}
	
	@Test
	public void testAdd_ValidationFails() {
		Cliente c = new Cliente(); //Tiene el nombre vacio
		assertThrows(RuntimeException.class, ()->{
			this.service.add(c);
		});
	}
	
}
