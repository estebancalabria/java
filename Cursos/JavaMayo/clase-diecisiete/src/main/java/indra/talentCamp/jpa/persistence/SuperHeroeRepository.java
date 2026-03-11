package indra.talentCamp.jpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indra.talentCamp.jpa.models.SuperHeroe;

@Repository
public interface SuperHeroeRepository extends JpaRepository<SuperHeroe, Integer> {

}
