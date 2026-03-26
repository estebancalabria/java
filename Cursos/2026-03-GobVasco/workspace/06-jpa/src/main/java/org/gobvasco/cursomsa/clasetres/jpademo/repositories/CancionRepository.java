package org.gobvasco.cursomsa.clasetres.jpademo.repositories;

import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

	//Convenciones de nombre
	List<Cancion> findByArtista(String artista);
	
	List<Cancion> findByTituloContaining(String titulo);
	
	@Query("Select c FROM Cancion c WHERE c.titulo LIKE '%?1%'")
	List<Cancion> buscarPorTituloConQuery(String titulo);
}
