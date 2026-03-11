package allianz.claseCinco;

import java.util.*;
import allianz.claseCinco.models.*;
import allianz.claseCinco.vista.Console;

public class Programa {

	public static void main(String[] args) {
		Console.writeLine("Bienvendios al Curso de Java : Clase Cinco");

		// Ejemplo 1
		/*
		 * Racional medio = new Racional(1,2); Racional otroMedio = new Racional(1,2);
		 * Racional resultado = medio.sumar(otroMedio);
		 * System.out.println(String.format("La suma de %s y %s es %s",
		 * medio.toString(), otroMedio.toString(), resultado.toString()));
		 */

		// Ejemplo 2
		/*
		 * Racional a = new Racional(1,3); Racional b = new Racional(1,3); Racional c =
		 * new Racional(1,3); Racional res = a.sumar(b).sumar(c);
		 * 
		 * //toString es un metodo especial //Siempre que necesite mostrar un objeto se
		 * llama solo //System.out.println(a); //Es lo mismo
		 * //System.out.println(a.toString()); //el toString() es un metodo especial se
		 * llama solo
		 * 
		 * System.out.println(String.format("%s + %s + %s= %s", a,b,c,res));
		 */

		// Prueba a ver si no pierdo decimales
		/*
		 * double a = (double)1/(double)3; double b = (double)1/(double)3; double c =
		 * (double)1/(double)3; double res = a + b + c; System.out.println(res);
		 */

		// Con Tipo de datos primitivo
		/*
		 * double xd = (double)1/(double)2; double yd = xd; yd = (double)1/(double)4;
		 * System.out.println(xd); //<<< Sin lugar a dudas muestra 0.5
		 * 
		 * //Con un tipo de datos objeto Racional x = new Racional(1,2); Racional y = x;
		 * //Tanto la variable x, como y apuntan al mismo lugar y.setDenominador(4);
		 * System.out.println(x); //<<<<< ACA MUESTA 0.25
		 */

		List<Figura> figuras = new ArrayList<Figura>();
		boolean finIngreso = false;
		
		while (!finIngreso) {
			Console.writeLine("Diga que figura queremos crear (Numeros)");		
			Figura figura = menuCrearFigura();
			if (figura != null) {
				figuras.add(figura);
			}else {
				finIngreso = true;
			}
		}
		
		for (Figura figura: figuras) {
			Console.writeLine(figura.toString());
			Console.writeLine(String.format("Permietro %.2f", figura.calcularPerimetro()));
			Console.writeLine(String.format("Area %.2f", figura.calcularArea()));
		}
		
	}

	private static Figura menuCrearFigura() {
		Figura figura = null;
		Console.writeLine("1. Circulo");
		Console.writeLine("2. Cuadrilatero");
		Console.writeLine("3. TrianguloEquilatero");
		Console.writeLine("4. TrianguloRectangulo");
		//En caso de que sea 5 va a devolver null
		Console.writeLine("5. Salir");
		int opcion = Console.promptInt("Defina que figura crear", "No Ingreso Numeros", 1, 5);
		switch (opcion) {
		case 1:
			figura = new Circulo(Console.promptInt("Ingrese el radio"));
			break;
		case 2:
			figura = new Cuadrilatero(Console.promptInt("Ingrese el lado 1"), Console.promptInt("Ingrese el lado "));
			break;
		case 3:
			figura = new TrianguloEquilatero(Console.promptInt("Ingrese el lado"));
			break;
		case 4:
			figura = new TrianguloRectangulo(Console.promptInt("Ingrese la base"),
					Console.promptInt("Ingrese la altura"));
			break;
		default:
			break;
		}
		return figura;
	}

}
