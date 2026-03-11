package org.ministerioEconomia.claseCinco;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import org.ministerioEconomia.models.Usuario;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bievenidos a la Clase ");
		
//		//Try-with-resources
//		try (Scanner lector = new Scanner(System.in)){
//		}
//		
//		//Es lo mismo que hacer
//		Scanner lector = new Scanner(System.in);
//		try {
//			///,,,,
//		}finally {
//		   lector.close();
//		}
		
		
		try (Scanner lector = new Scanner(System.in)){
			

			System.out.println("Vamos a Ingresar los datos de nuestro usuario");

//			//Ejemplo 1 : Sin Encapsulamiento (No respeta las convenciones del lenguaje			
//			Usuario usuario = new Usuario();
//			usuario.id = 1;
//			usuario.nombre = "Josefa";
//			usuario.fechaDeNacimiento = LocalDate.of(1979, Month.OCTOBER, 9);
//			usuario.departamento = "Comunicaciones";
			
			//Ejemplo 2 : Con Encapsulamiento
//			Usuario usuario = new Usuario();
//			//Ahora tengo un objeto INCONSISTENTE
//			usuario.setId(1);
//			//usuario.setNombre("josefa"); //Si pongo el nombre con minuscula lanza una excepcion
//			usuario.setNombre("Josefa");
//			usuario.setFechaDeNacimiento(LocalDate.of(1979, Month.OCTOBER, 9));
//			usuario.setDepartamento("Conumicaciones");
			//Recien despues de inicializarlo es un objeto CONSISTENTE
			
			//Ejemplo 3 : Incorporacion del constructor
			//Usuario usuario = new Usuario(1, "Josefa", "Comunicaciones", LocalDate.of(1979, Month.OCTOBER, 9));
			//Quitamos el parametro ID del contructor
			Usuario usuario = new Usuario("Josefa", "Comunicaciones", LocalDate.of(1979, Month.OCTOBER, 9));
			
			//Ahora les hago una pregunta
			//Ahora tengo un objeto consistente
			//Que opciones tengo para asegurarme simpre tenerun objeto inconsistnete
			
			//1)Agregar una validacion al setter
			try {
				usuario.setNombre("");  //<<<<< Va en contra de lo que hable! Tengo un objeto inconcsistente	
			}catch(Error err) {
				System.err.println("Se intento cambiar el nombre por uno vacio y dio este error");
				System.err.println(err.getMessage());
			}
			
			//2)Sacandole el setter : Los atributos sin setter se llaman de solo lectura
			//usuario.setId(23); Ahora no me compila si trato de cambiar el ID
			
			
			//Muestro el usuario
			System.out.println(usuario);  //Se llama automaticamente al metodo oculto toString()
			//System.out.println(usuario.toString());  //Se llama automaticamente al metodo oculto toString()
			
			//Si quiero mostrar a mi usuario en distintas partes de mi programa de forma consistente
			//En todas ellas escribiria lo mismo y estaria repitiendo codigo
//			System.out.println(MessageFormat.format("({0} {1} {2} {3})", 
//					usuario.getId(), 
//					usuario.getNombre(), 
//					usuario.getFechaDeNacimiento(), 
//					usuario.getDepartamento()));
			
			//Que pasa si ahora hago lo siguiente
			//Usuario angel = new Usuario(1, "Angel", "Aplicaciones", LocalDate.of(1994, Month.OCTOBER, 9));
			//Quitamos el parametro ID del contructor
			Usuario angel = new Usuario("Angel", "Aplicaciones", LocalDate.of(1994, Month.OCTOBER, 9));
			System.out.println(angel);
			
		} /* try Scanner*/
	}
	/*void main*/
}/*class Program*/
