package org.indra.claseNueve.models;

import java.text.MessageFormat;

import lombok.*;

@ToString
public class Movil extends BusinessObject {

	@Getter
	@Setter
	private String marca;

	@Getter
	@Setter
	private int tamaño;

	@Override
	public void validate() throws ModelValidationException {
		super.validate();

		if ((marca == null) || (marca.trim().length() == 0)) {
			throw new ModelValidationException("marca", "Attribute cannot be empty.");
		}
		if ((tamaño <= 5) || (tamaño > 10)) {
			throw new ModelValidationException("tamaño", MessageFormat.format("Range error. Min {0} Max {1}", 5, 10));
		}
	}
}
