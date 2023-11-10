package org.itnow.cursoSpring.clasecuatro;

import static org.junit.jupiter.api.Assertions.*;

import org.itnow.cursoSpring.clasecuatro.controllers.AutomovilController;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("Test")
class ClaseCuatroApplicationTests {
	
	@Autowired
	AutomovilController controller;

	@Test
	void testAutomovilController() {
		//List<Automovil> automoviles = ;
		int totalAntes = controller.index().getBody().size();
		
		Automovil auto = new Automovil("Tesla","M3",2003);
		controller.post(auto);
		
		int totalDespues = controller.index().getBody().size();
		
		assertEquals(totalAntes+1, totalDespues);
	}
	
	@Test
	void contextLoads() {
		
	}

}

