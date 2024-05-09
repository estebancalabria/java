package org.talentCamp.claseUnoSpringData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.talentCamp.claseUnoSpringData.models.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Integer> {
}
