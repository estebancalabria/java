package org.indra.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.indra.models.Pelicula;
import org.indra.repository.PeliculaMockRepoFactory;
import org.indra.repository.PeliculaRepositoryFactory;
import org.indra.services.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PeliculaTest {

	/*public PeliculaTest() {
		PeliculaRepositoryFactory.configureClass(PeliculaMockRepoFactory.class);	
	}*/
	
	@BeforeEach
	void setUp() throws Exception {
		PeliculaRepositoryFactory.configureClass(PeliculaMockRepoFactory.class);
		//PeliculaRepositoryFactory.configureClass(PeliculaDbRepoFactory.class);
	}

	@Test
	void testAddPelicula() throws Exception {
		LocalDate hoy = LocalDate.now();
		
		PeliculaService service = new PeliculaService();
		Pelicula p = new Pelicula();
		p.setTitulo("El señor de los anillos");
		p.setDirector("Peter Jackson");
		p.setDuracion(260);
		p.setFechaSalida(hoy);
		service.add(p);
		
	    Pelicula pRecu = service.getByName("El señor de los anillos");
	    assertEquals("El señor de los anillos", pRecu.getTitulo());
	    assertEquals("Peter Jackson", pRecu.getDirector());
	    assertEquals(260, pRecu.getDuracion());
	    assertEquals(LocalDate.now(), pRecu.getFechaSalida());
	}
	
	@Test
	void testUpdatePelicula() throws Exception {
		PeliculaService service = new PeliculaService();
        
		Pelicula p = service.getById(1);
        p.setTitulo("Alicia en el pais de las maravillas");
        p.setDuracion(100);
        service.update(p);
        
        Pelicula laMisma = service.getById(1);
        assertEquals("Alicia en el pais de las maravillas", laMisma.getTitulo());
        assertEquals(100, laMisma.getDuracion());
	}
	
	@Test
	void testDeletePelicula() throws Exception {
		//Recupero todas
		//Cuento cuantas hay
		//Borro 1
		//Vuelvo a recuperar todas
		//Tiene que haber 1 menos
        PeliculaService service = new PeliculaService();
        List<Pelicula> peliculas = service.getAll();
        int numPeliculas = peliculas.size();
        
        service.delete(2);
        
        List<Pelicula> peliculasNuevas = service.getAll();
        int numPeliculasNuevas = peliculasNuevas.size();
        
        
        assertEquals(numPeliculas-1, numPeliculasNuevas);
	}

}
