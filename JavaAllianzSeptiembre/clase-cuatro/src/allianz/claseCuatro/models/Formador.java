package allianz.claseCuatro.models;

import java.time.LocalDate;
import java.util.List;

public class Formador extends Persona {
	
	//Esta es una variable estatica. La comparten todos los formadores
	private static int CANTIDAD_FORMADORES = 0;
	
	private int id;
	
	//Refactoring 4 - Herencia
	//private String nombre;
	//private String apellido;
	//private Sexo sexo;
	//private LocalDate fechaDeNacimiento;
	
	private LocalDate fechaIncioActividad;
	private List<String> cursosDictados;
	private List<String> tecnologias;
	
	public Formador(String nombre, String apellido, Sexo sexo, LocalDate fechaDeNacimiento,
			LocalDate fechaIncioActividad) {
		super(nombre, apellido, sexo, fechaDeNacimiento);
		
		CANTIDAD_FORMADORES++;  
		//o Formador.CANTIDAD_FORMADORES
		this.id = Formador.CANTIDAD_FORMADORES;
		
		//Refactoring 4 - Herencia
		//this.nombre = nombre;
		//this.apellido = apellido;
		//this.sexo = sexo;
		//this.fechaDeNacimiento = fechaDeNacimiento;
		
		this.fechaIncioActividad = fechaIncioActividad;
	}

	public Formador(String nombre, String apellido, Sexo sexo) {
		this(nombre, apellido, sexo, LocalDate.now(), LocalDate.now());
	}
	
	public Formador(String nombre, String apellido, Sexo sexo, LocalDate fechaDeNacimiento) {
		this(nombre, apellido, sexo, fechaDeNacimiento, fechaDeNacimiento);
	}

	//Refactoring 4 - Herencia
	/*public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}*/

	public LocalDate getFechaIncioActividad() {
		return fechaIncioActividad;
	}

	public void setFechaIncioActividad(LocalDate fechaIncioActividad) {
		this.fechaIncioActividad = fechaIncioActividad;
	}

	public int getId() {
		return id;
	}

	//Refactoring 4 - Herencia
	/*public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}*/

	public List<String> getCursosDictados() {
		return cursosDictados;
	}

	public List<String> getTecnologias() {
		return tecnologias;
	}
	
	public int getAñosDeExperiencia() {
		return LocalDate.now().getYear() - this.getFechaIncioActividad().getYear();
	}
	
	@Override /*El @Override es opcional y es como un comentario que indica que este metodo ya estaba y lo estoy sobreescribiendo*/
	public String toString() {
		return String.format("(%d) - %s %s de %d años de experiencia",
				this.getId(),
				this.getNombre(), 
				this.getApellido(), 
				this.getAñosDeExperiencia());
	}
	
}
