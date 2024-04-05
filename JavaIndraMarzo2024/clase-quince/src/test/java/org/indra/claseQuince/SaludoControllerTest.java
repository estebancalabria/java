package org.indra.claseQuince;

import static org.junit.jupiter.api.Assertions.*;
import org.indra.claseQuince.controllers.SaludoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SaludoControllerTest {
	
	@Autowired
	private SaludoController controller;
	
	@Test
	public void testSaludoController() {
		//assertEquals("Hola ambiente productivo", controller.saludar());
		assertEquals("Hola ambiente test", controller.saludar());
		
	}
}
