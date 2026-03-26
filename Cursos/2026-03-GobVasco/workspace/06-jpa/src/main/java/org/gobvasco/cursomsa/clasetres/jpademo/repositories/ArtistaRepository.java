package org.gobvasco.cursomsa.clasetres.jpademo.repositories;

import org.gobvasco.cursomsa.clasetres.jpademo.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository  extends JpaRepository<Artista, Long> {

}
