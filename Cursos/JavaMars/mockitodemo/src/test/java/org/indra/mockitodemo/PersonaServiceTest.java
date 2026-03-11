package org.indra.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.indra.mockitodemo.models.Persona;
import org.indra.mockitodemo.repositories.IPersonaRepository;
import org.indra.mockitodemo.services.PersonaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PersonaServiceTest {

	@InjectMocks
	PersonaService service = new PersonaService();
	
	@Mock
	IPersonaRepository repository;
	
	@Test
	public void testGetById() {
		when(this.repository.get(1)).thenReturn( new Persona() {{setNombre("Juan"); setApellido("Perez");}});
		
		Persona p = this.service.getById(1);
		assertEquals("Juan", p.getNombre());
		assertEquals("Perez", p.getApellido());
	}
	
	@Test(expected = Exception.class)
	public void testAddWithEmptyName() throws Exception {
		Persona p = new Persona();
		this.service.add(p);
	}
	
	@Test
	public void testAddSuccess() throws Exception {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Perez");
		this.service.add(p);
		
		verify(repository).add(p);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetNotExisting() {
		doThrow(new RuntimeException("Not found")).when(this.repository).get(999);
		service.getById(999);
		verify(repository).get(999);
	}
}
