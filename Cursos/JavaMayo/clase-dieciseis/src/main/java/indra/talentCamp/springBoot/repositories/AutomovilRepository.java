package indra.talentCamp.springBoot.repositories;

import indra.talentCamp.springBoot.models.Automovil;

public interface AutomovilRepository {
	public Automovil findById(int id);
	public void save(Automovil autoNuevo);
}
