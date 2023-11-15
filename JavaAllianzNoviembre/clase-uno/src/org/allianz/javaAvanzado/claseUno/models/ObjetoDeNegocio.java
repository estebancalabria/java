package org.allianz.javaAvanzado.claseUno.models;

import lombok.*;

@ToString
public abstract class ObjetoDeNegocio {

	private static int ULTIMO_ID = 0;
	@Getter @Setter
	private int id;
	
	public ObjetoDeNegocio() {
		ObjetoDeNegocio.ULTIMO_ID ++;
		this.id = ObjetoDeNegocio.ULTIMO_ID;
	}
}
