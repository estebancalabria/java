package org.ministerioEconomia.models;

import java.util.UUID;

/*No la voy a instanciar : la cree para no repetir codigo y que tengan el comportamiento comun entre varias clases*/
public abstract class ObjetoDeNegocio {
	protected UUID id;

	public ObjetoDeNegocio() {
		super();
		this.id = UUID.randomUUID();
	}

}
