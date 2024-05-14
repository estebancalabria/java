package org.talentCamp.claseUnoSpringData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.talentCamp.claseUnoSpringData.models.Artista;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    //Query con SQL en vez de JPQL
    @Query(value = "select * from artists where artist like 'D%' ", nativeQuery = true)
    List<Artista> findEmpiezanConD();

    @Query(value = "select * from artists where artist like CONCAT(:comienzo,'%') ", nativeQuery = true)
    List<Artista> findEmpiezanCon(@Param("comienzo") String comienzo);
}
