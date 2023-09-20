package org.indra.clasequince.models;

import lombok.*;

@Getter @Setter
public class Cancion extends ObjetoDeNegocio {
	private String nombre;
	private String artista;
	private int duracionSegundos;
}
