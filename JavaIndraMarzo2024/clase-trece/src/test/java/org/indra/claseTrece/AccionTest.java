package org.indra.claseTrece;

import static org.testng.Assert.*;
import org.indra.claseTrece.models.Accion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccionTest {
	
	@Test
	public void testValidacionAccion() {
		
		//valido Simbolo
		Accion accion = new Accion();
		assertThrows(Error.class, ()->{
			accion.validar();
		});
		
	}

}
