package allianz.claseTres.models;

import java.time.LocalDate;

public class Automovil {
	private String matricula;
	private String marca;
	private int a�o;
	private boolean nuevo = true;  //La declaro con un valor por defecto
	
	public Automovil(String matricula, String marca, int a�o) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.a�o = a�o;
	}

	public Automovil(String matricula, String marca) {
		this(matricula, marca, LocalDate.now().getYear());
	}

	public boolean isNuevo() {
		return nuevo;
	}
}
