package org.indra.claseSeis.tests;

import org.indra.claseSeis.models.*;
import org.indra.claseSeis.persistence.ClienteMockRepository;
import org.indra.claseSeis.services.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ClienteServiceTest {

	@Test
	void testRegistrarClienteOK() throws Exception {
		//PARA LAS PRUEBAS USO EL ClienteMockRepository
		//PARA EL CODIGO EN PRODUCCION ClienteFileRepository
		ClienteService service = new ClienteService(new ClienteMockRepository());
		Cliente c = Cliente.builder().withDni(12).withNombre("Esteban").withApellido("Calabria")
				.withFechaNacimiento(LocalDate.now()).build();

		service.registrarCliente(c);

		Cliente cRecuperado = service.recuperarCliente(c.getId());
		assertEquals(c.getDni(), cRecuperado.getDni());
		assertEquals(c.getNombre(), cRecuperado.getNombre());
		assertEquals(c.getApellido(), cRecuperado.getApellido());
		assertEquals(c.getFechaNacimiento(), cRecuperado.getFechaNacimiento());
	}

	@Test
	void testRegistrarCliente_NombreVacio() {
		assertThrows(Error.class, () -> {
			Cliente c = Cliente.builder().withDni(13).withNombre("").withApellido("Martinez")
					.withFechaNacimiento(LocalDate.now()).build();
			ClienteService service = new ClienteService(new ClienteMockRepository());
			service.registrarCliente(c);
		});
	}

}
