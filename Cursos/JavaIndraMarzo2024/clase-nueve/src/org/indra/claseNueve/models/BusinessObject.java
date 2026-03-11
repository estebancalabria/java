package org.indra.claseNueve.models;

import lombok.*;

public abstract class BusinessObject {

	@Getter @Setter
	private int id = 0;
	
	public boolean isNew() {
		return id == 0;
	}
	
	public void validate() throws ModelValidationException {
		//No lo voy a hacer abstracto por si alguna subclase no lo quiere completar
	}
}
