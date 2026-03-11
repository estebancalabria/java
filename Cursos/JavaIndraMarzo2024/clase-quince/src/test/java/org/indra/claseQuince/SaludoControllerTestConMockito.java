package org.indra.claseQuince;

import static org.junit.jupiter.api.Assertions.*;
import org.indra.claseQuince.controllers.SaludoController;
import org.indra.claseQuince.services.SaludoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaludoControllerTestConMockito {

	@Autowired
	@InjectMocks
	private SaludoController controller;
	
	@Mock
	private SaludoService service;
	
	@Test
	public void testSaludoController() {
		when(service.saludar()).thenReturn("hola con mockito");
		
	   assertEquals("hola con mockito", controller.saludar());
	}
	
	
}
