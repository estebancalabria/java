package org.talentCamp.claseUnoSpringData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.talentCamp.claseUnoSpringData.models.Cancion;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    //Query en JPQL
    @Query(value = "SELECT c from Cancion c where c.duracionSegundos <= 120")
    List<Cancion> cancionesMenosDosMinutos();

    @Query(value = "SELECT c from Cancion c where c.duracionSegundos <= :max")
    List<Cancion> cancionesMenosDuracionQue(@Param("max") int duracionMax);
}
