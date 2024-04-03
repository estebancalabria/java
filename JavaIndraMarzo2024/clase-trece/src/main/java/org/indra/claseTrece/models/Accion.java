package org.indra.claseTrece.models;

import lombok.*;

public class Accion {
	@Setter @Getter
	private String simbolo;
	@Setter @Getter
	private String nombre;
	@Setter @Getter
	private double ultimaCotizacion;
}
