package indra.talentCamp.dependencyInjection.reflection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import indra.talentCamp.dependencyInjection.reflection.MockRepository;
import indra.talentCamp.dependencyInjection.reflection.PartyService;
import indra.talentCamp.dependencyInjection.reflection.PartyServiceVersion2;

public class PartyServiceTest {
	
    @Before
    public void configure() {
        // tiempo de configuracion (usar MOCK no estamos en produccion al hacer tests)
        PartyService.registerRepositoryClass(MockRepository.class);
		PartyServiceVersion2.registerRepositoryClass("indra.talentCamp.dependencyInjection.reflection.MockRepository");

    }
    
    @Test
    public void test() {
        //tiempo de ejecucion
        PartyService service = new PartyService();
        service.haveFun();
        
        assertEquals(true, true);  // asserts correspondientes
    }
    
    @Test
    public void test2() {
    	PartyServiceVersion2 service = new PartyServiceVersion2();
    	service.haveFun();
    }
}
