package org.ministerioEconomia.claseSiete;

import java.util.*;

import org.ministerioEconomia.claseSiete.models.*;

public class Program {

	public static void main(String[] args) {
		//Enunciado : Vamos a trabajar con figuras.
		//            El sistema va a dibujar figuras en el plano y va a permitir calcular area y el perimetro
		//            Circulos, TriangulosRectangulos, Cuadrilateros
		
		System.out.println("Bienvenidos a la Clase Siete");
		
		try (Scanner lector = new Scanner(System.in)){
			//Figura figura = new Figura(); no puedo instanciar una clase abstracta
			List<Figura> figuras = new ArrayList<>();
			
			//Creo unas figuras
			Circulo circulo = new Circulo(100);			
			Cuadrilatero cuadrilatero = new Cuadrilatero(12,12);
			TrianguloRectangulo triangulo = new TrianguloRectangulo(10, 10);
			Figura otroTriangulo = new TrianguloRectangulo(3, 3);
			
			figuras.add(circulo);
			figuras.add(cuadrilatero);
			figuras.add(triangulo);
			figuras.add(otroTriangulo);
			
//			Figura f; //No puedo instanciar figuraa porque figura es una clse abstracta
//			f = circulo; //Apunto f al circulo
//			System.out.println(f);
//			
//			f = cuadrilatero;
//			System.out.println(f);
//			
//			f = triangulo;
//			System.out.println(f);
			
			for (Figura figura : figuras) {
				System.out.println(figura);
				System.out.println("Su area es : " + figura.calcularArea());
				System.out.println("--------------------------------------");
			}
				
		}

	}

}
