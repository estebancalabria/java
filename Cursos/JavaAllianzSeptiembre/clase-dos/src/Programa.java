
//import java.util.Scanner;
import java.text.*;
import java.time.LocalDate;

public class Programa {

	public static void main(String[] args) {
		Console.DEFAULT_TYPE_INT_ERROR_MESSAGE = "Must enter integer";

		Console.writeLine("Bienevenidos a la Clase 2");

		//
		// Primera Parte. Clase Console. (Agrupador de metodos de clase/static)
		//

		// String nombre = Console.prompt("¿Como te llamas?");
		// int edad = Console.promptInt("¿Que edad tienes?", "Debe Ingresar un numero
		// entre 1 y 100", 1, 100);
		// int documento = Console.promptInt("¿Cual es tu documento?");

		// Console.writeLine("Hola " + nombre + " de " + edad + " años con documento " +
		// documento);
		// Console.writeLine(String.format("Hola %s de %d años con documento %d",
		// nombre, edad, documento));
		// Console.writeLine(MessageFormat.format("Hola {0} de {1} años con documento
		// {2}", nombre, edad, documento));
		// System.out.format("Hola %s de %d años con documento %d \n", nombre, edad,
		// documento);

		//
		// Segunda Parte. Clase Persona (objetos)
		//
		Persona persona = new Persona();
		persona.nombre = Console.prompt("¿Como te llamas?");
		//persona.edad = Console.promptInt("¿Que edad tienes?", "Debe Ingresar un numero entre 1 y 100", 1, 150);
		persona.fechaDeNacimiento = LocalDate.of(
				Console.promptInt("¿En que año naciste?", "Debe Ingresar un numero entre 1900 y 2050", 1900, 2050), 
				Console.promptInt("¿En que mes naciste?", "Debe Ingresar un numero entre 1 y 100", 1, 12), 
				Console.promptInt("¿Que dia naciste?", "Debe Ingresar un numero entre 1 y 31", 1, 31));
		
		persona.documento = Console.promptInt("¿Cual es tu documento?");

		Console.writeLine(String.format("Hola %s nacido en %s con documento %d", persona.nombre, persona.fechaDeNacimiento, persona.documento));

		Console.writeLine("Fin del programa");
	}
}
