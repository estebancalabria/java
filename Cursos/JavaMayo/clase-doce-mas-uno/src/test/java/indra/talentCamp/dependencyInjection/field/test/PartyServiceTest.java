package indra.talentCamp.dependencyInjection.field.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import indra.talentCamp.dependencyInjection.field.*;

public class PartyServiceTest {
	
	PartyService service;
	
	//Tiempo De Configuracion
	@Before
	public void configure() {
		service = new PartyService();
		service.setRepository(new MockRepository());
	}

	@Test
	public void test() {
		//Tiempo de Ejecucion
		service.haveFun();
		assertEquals(true,true);
	}

}
