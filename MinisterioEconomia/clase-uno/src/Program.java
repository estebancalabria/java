import java.util.Date;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		/*
		// Ejemplo 1
		// Mi primer "Hola Mundo en Java"
	    System.out.println("Hola Mundo");
		*/
		
		/*
		// Ejemplo 2
		// Uso de Variables
		String saludo = "Bienvenidos al mundo de Java";
		System.out.println(saludo);
		*/

		/*
		// Ejemplo 3:
		// Tipo de dato int
		// Conversiones Implicitas
		String nombre = "Esteban";
		int edad = 43;
		//Conversion Implicita 
		System.out.println("Mi nombre es " +  nombre + " y tengo " + edad);
		*/
		
		
		/*
		//Ejemplo 4
		// Chequeo de tipos u conversion
		//Error de asignacion, tipos incomptatibles
		//int a;
		//a = "Hola";
		
		//Otra asignacion incompatible
		String n;
		n =  12; //Otra asignacion incompatible
		//n = "" + 12;  //Esto si funciona porque hace una conversion implicita
		*/
		
		/*
		int edad = 43;
		double altura = 1.73;
		
		//edad = altura;   //No puedo guardar una variable de tipo punto flotante en un int
		altura = edad;     //Una conversion implicita, una variable flotante si puede almacenar un int
		*/
		
		/*
		Date fecha = new Date(1980, 8, 20);
		System.out.println(fecha);
		*/
		
		
		// Ejemplo 5
		// Nuestro primer programa interactivo
		
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Ingrese su nombre:");
		String nombre = lector.nextLine();
		
		System.out.println("Ingrese su profesion");
		String profesion = lector.nextLine();
		
		System.out.println("Ingrese su edad");
		
		try {
			int edad = lector.nextInt();
			System.out.println("Bienvenido " + nombre + " de profesion " + profesion + " de edad " + edad);
		} catch (Exception ex) {
			System.out.println("No ingresaste bien tu edad");
			System.out.println("Bienvenido " + nombre + " de profesion " + profesion);
		}
		
		
	}
}
