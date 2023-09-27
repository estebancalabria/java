package allianz.claseSeis.models;

import java.time.LocalDate;

public class Cliente implements Validable{

	private int documento = 0;
	private String nombre = null;
	private String apellido = null;
	private LocalDate fechaDeNacimiento = null;
	private boolean valido = false;

	public Cliente() {
		this.valido = false;
	}

	public Cliente(int documento, String nombre, String apellido, LocalDate fechaDeNacimiento) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public void validate() throws ValidationException{
		
		if (this.documento == 0) {
			throw new ValidationException("Validation Error : Cliente : Documento Invalido");
		}

		if ((this.nombre == null) || (this.nombre.length() == 0)) {
			throw new ValidationException("Validation Error : Cliente : Nombre Incompleto");
		}

		if ((this.apellido == null) || (this.apellido.length() == 0)) {
			throw new ValidationException("Validation Error : Cliente : Apellido Incompleto");
		}

		if (this.fechaDeNacimiento == null) {
			throw new ValidationException("Validation Error : Cliente : Fecha Nacimiento Incompla");
		}

		this.valido = true;
	}
	
	public boolean isValid() {
		return this.valido;
	}

	@Override
	public String toString() {
		return String.format("%d - %s,%s - %s", this.getDocumento(), this.getApellido().toUpperCase(), this.getNombre(),
				this.getFechaDeNacimiento().toString());
	}
	


}
