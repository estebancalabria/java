package org.indra.claseTrece;

import org.indra.claseTrece.models.Accion;
import org.indra.claseTrece.repositories.AccionRepository;
import org.indra.claseTrece.services.CrudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccionServiceTest {

	@Mock
	AccionRepository repository;
	
	@Autowired
	@InjectMocks
	CrudService<String, Accion> service;
	
	@Test
	void testFindById() {
		when(repository.findById("LTR")).thenReturn(Optional.of(new Accion() {{
			setSimbolo("LTR");
			setNombre("Light Transport Industries");
			setUltimaCotizacion(100);
		}}));
		
		assertEquals("LTR" , service.readById("LTR").getSimbolo());
		
	}
}
