package allianz.claseTres.models;

import java.time.LocalDate;

public class Automovil {
	private String matricula;
	private String marca;
	private int año;
	private boolean nuevo = true;  //La declaro con un valor por defecto
	
	public Automovil(String matricula, String marca, int año) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.año = año;
	}

	public Automovil(String matricula, String marca) {
		this(matricula, marca, LocalDate.now().getYear());
	}

	public boolean isNuevo() {
		return nuevo;
	}
}
