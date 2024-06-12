package org.ministerioEconomia.claseSiete;

import java.util.Scanner;

import org.ministerioEconomia.claseSiete.models.*;

public class Program {

	public static void main(String[] args) {
		//Enunciado : Vamos a trabajar con figuras.
		//            El sistema va a dibujar figuras en el plano y va a permitir calcular area y el perimetro
		//            Circulos, TriangulosRectangulos, Cuadrilateros
		
		System.out.println("Bienvenidos a la Clase Siete");
		
		try (Scanner lector = new Scanner(System.in)){
			//Figura figura = new Figura(); no puedo instanciar una clase abstracta
			
			//Creo unas figuras
			Circulo c = new Circulo(100);
			System.out.println(c.getId());
			System.out.println(c.getNombre());
			
			Cuadrilatero c2 = new Cuadrilatero();
			System.out.println(c2.getId());
			System.out.println(c2.getNombre());
			
			Figura f; //No puedo instanciar figuraa porque figura es una clse abstracta
			f = c;
			System.out.println(f.getId());
			System.out.println(f.getNombre());
			f = c2;
			System.out.println(f.getId());
			System.out.println(f.getNombre());
		}

	}

}
