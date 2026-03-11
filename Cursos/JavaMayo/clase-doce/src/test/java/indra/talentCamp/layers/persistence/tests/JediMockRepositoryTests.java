package indra.talentCamp.layers.persistence.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import indra.talentCamp.layers.models.Jedi;
import indra.talentCamp.layers.persitence.JediMockRepository;
import indra.talentCamp.layers.persitence.NotFoundException;
import indra.talentCamp.layers.persitence.SaveErrorException;

class JediMockRepositoryTests {

	@Test
	void shouldThrowAnExceptionIfNotFound() {
		JediMockRepository repo = new JediMockRepository();
		assertThrows(NotFoundException.class, ()-> repo.findById(28));
	}

	@Test
	void shouldSaveAndThenFind() throws NotFoundException, SaveErrorException {
		JediMockRepository repo = new JediMockRepository();
		
		Jedi joda = new Jedi("Joda", 100000, Color.GREEN);
		
		repo.save(joda);
		
		Jedi jodaRenacido = repo.findById(1);
		
		assertEquals( joda.getNombre(), jodaRenacido.getNombre());
	}

}
