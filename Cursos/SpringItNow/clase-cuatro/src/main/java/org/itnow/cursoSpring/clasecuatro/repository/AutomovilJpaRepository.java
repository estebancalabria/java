package org.itnow.cursoSpring.clasecuatro.repository;

import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovilJpaRepository extends JpaRepository<Automovil, Integer>{

}
