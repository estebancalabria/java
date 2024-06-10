package org.ministerioEconomia.models;

import java.text.MessageFormat;
import java.time.LocalDate;

public class Usuario {

	//Una variable estatica es aquella que comparten todas las instancias de la misma clase
	private static int ULTIMO_ID = 1;
	
	// Encapsulamiento
	// -- Los atributos describen el estado interno del los objetos de la clase
	// Usuario
	// -- Los atributos son algo interno a lo que no se debe acceder directamente
	// -- Para evitar que se acceda directamente java tiene "niveles de visibilidad"
	// -- Convencion del lenguaje (1er mandamiento) : Los atriburos van com private
	// o protected
	private int id;
	private String nombre;
	private String departamento;
	private LocalDate fechaDeNacimiento;
	
	//El constructor es el metodo que se llama cuando creo un objeto (hago new)
	//tiene como objetivo inicializar el objeto 
	//(me sirve para asegurarme tener un objeto consistente al momento de la creacion)

	//public Usuario(int id, String nombre, String departamento, LocalDate fechaDeNacimiento) {
	public Usuario(String nombre, String departamento, LocalDate fechaDeNacimiento) {
		this.id = ULTIMO_ID;
		this.nombre = nombre;
		this.departamento = departamento;
		this.fechaDeNacimiento = fechaDeNacimiento;
	    ULTIMO_ID++;
	}
	

	// setter
	public void setNombre(String value) {
		//Opcion 1 Inconsitencia... valido en el setter
		if (value.isBlank()) {
			throw new Error("Validacion: El nombre del Usuario no puede quedar vacio");

		}
		
		// Los setter permiten controlar que los datos que lleguen al objeto sean
		// correctos
		if (!Character.isUpperCase(value.charAt(0))) {
			throw new Error("Validacion: El nombre del Usuario debe empezar con Mayuscula");
		}	

		this.nombre = value;
	}

	public int getId() {
		return id;
	}

	//Lo comento para que la propiedad id sea de solo lectura (read-only)
	//Opciones para asegurar objeto consistente -
	//     Sacandole el setter : Los atributos sin setter se llaman de solo lectura
	/*public void setId(int id) {
		this.id = id;
	}*/

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	// getter
	public String getNombre() {
		// Otra opcion es guardarlo como viene y al devolverlo convertir la primera a
		// Mayuscula
		return this.nombre;
	}

	@Override
	public String toString() {
		return MessageFormat.format("({0} {1} {2} {3})", 
				this.getId(), 
				this.getNombre(), 
				this.getFechaDeNacimiento(), 
				this.getDepartamento());
	}
} /* class Usuario */
