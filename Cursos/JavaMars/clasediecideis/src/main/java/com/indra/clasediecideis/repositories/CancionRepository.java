package com.indra.clasediecideis.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.indra.clasediecideis.models.*;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Integer>  {

}
