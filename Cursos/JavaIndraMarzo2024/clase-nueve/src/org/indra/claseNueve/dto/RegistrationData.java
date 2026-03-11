package org.indra.claseNueve.dto;

import lombok.Getter;
import lombok.Setter;

//Los DTO solo tienen getters y setters y el usuario en la Vista llena todos estos datos
public class RegistrationData {
	@Getter @Setter
	private String model;
	@Getter @Setter
	private int year;
	@Getter @Setter
	private String color;
	
	@Getter @Setter
	private String marca;

	@Getter @Setter
	private int tamaño;
}
