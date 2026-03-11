package org.allianz.javaAvanzado.claseUno.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.allianz.javaAvanzado.claseUno.infraestructura.DummyLog;
import org.allianz.javaAvanzado.claseUno.repository.*;
import org.allianz.javaAvanzado.claseUno.models.*;
import org.allianz.javaAvanzado.claseUno.services.*;
import org.junit.jupiter.api.Test;

class ClienteServiceTest {

	@Test
	void testObtenerClienteNoDocumentoExitente() throws ServiceException {
		Exception ex =assertThrows(ServiceException.class, ()->{
			ClienteService service = new ClienteServiceImplementation(new ClienteMemoryRepository(new DummyLog()));
			service.obtenerClientePorDocumento(1233);
		});
		
		assertEquals("No existe cliente registrado con el documento 1233", ex.getMessage());
	}
	
	@Test
	void testObtenerClienteExistente() throws ServiceException {
		ClienteService service = new ClienteServiceImplementation(new ClienteMemoryRepository(new DummyLog()));
		service.registrarNuevoCliente(new Cliente(100, "Juan", "Perez"));
		Cliente recuperado = service.obtenerClientePorDocumento(100);
		assertEquals(100, recuperado.getDocumento());
		assertEquals("Juan", recuperado.getNombre());
		assertEquals("Perez", recuperado.getApellido());
	}
	
	@Test
	void testObtenerPorApellidos() throws ServiceException {
		ClienteService service = new ClienteServiceImplementation(new ClienteMemoryRepository(new DummyLog()));
		service.registrarNuevoCliente(new Cliente(100, "Juan", "Perez"));
		service.registrarNuevoCliente(new Cliente(120, "Juan", "Gomez"));
		service.registrarNuevoCliente(new Cliente(130, "Pedro", "Perez"));
		service.registrarNuevoCliente(new Cliente(150, "Raul", "Perez"));
		
		List<Cliente> losPerez = service.obtenerClientesPorApellido("Perez");
		assertEquals(3, losPerez.size());
	}
	
	
	
	

}


