package org.allianz.hellospringboot.repositories;

import org.allianz.hellospringboot.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{

}
