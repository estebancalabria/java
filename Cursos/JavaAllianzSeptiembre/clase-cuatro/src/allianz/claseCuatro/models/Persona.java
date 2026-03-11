package allianz.claseCuatro.models;

import java.time.LocalDate;

public abstract class Persona {
	
	private String nombre;
	private String apellido;
	private Sexo sexo;
	//Refactoring 4 - Herencia
	//Nivel de visibilidad protected ---> Es visible solo para las subclases
	//private LocalDate fechaDeNacimiento;
	protected  LocalDate fechaDeNacimiento;
	
	public Persona(String nombre, String apellido, Sexo sexo, LocalDate fechaDeNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
}
