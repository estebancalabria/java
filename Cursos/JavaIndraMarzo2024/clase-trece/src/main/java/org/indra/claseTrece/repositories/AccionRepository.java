package org.indra.claseTrece.repositories;

import org.indra.claseTrece.models.Accion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//La implementacion de esta interfaz se genera automaticamente
//JPA se encarga de generar la implementacion de esta clase automaticamente
@Repository
public interface AccionRepository extends JpaRepository<Accion, String>{

}
