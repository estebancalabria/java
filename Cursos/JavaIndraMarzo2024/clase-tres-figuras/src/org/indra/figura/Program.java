package org.indra.figura;

import java.text.MessageFormat;
import java.util.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Circulo circulo = new Circulo(1, 1, 3.0); Rectangulo rectangulo = new
		 * Rectangulo(2, 2, 3.0, 3.0); TrianguloRectangulo trianguloRectangulo = new
		 * TrianguloRectangulo(3, 3, 3.0, 3.0); double areaCirculo =
		 * circulo.calcularArea(); double perimetroCirculo =
		 * circulo.calcularPerimetro(); double areaRectangulo =
		 * rectangulo.calcularArea(); double perimetroRectangulo =
		 * rectangulo.calcularPerimetro(); double areaTrianguloRectangulo =
		 * trianguloRectangulo.calcularArea(); double perimetroTrianguloRectangulo =
		 * trianguloRectangulo.calcularPerimetro(); System.out.println("Círculo:\n");
		 * System.out.println("Área: " + areaCirculo + ", perímetro: " +
		 * perimetroCirculo + "\n"); System.out.println("Rectángulo:\n");
		 * System.out.println("Área: " + areaRectangulo + ", perímetro: " +
		 * perimetroRectangulo + "\n"); System.out.println("Triángulo Rectángulo:\n");
		 * System.out.println("Área: " + areaTrianguloRectangulo + ", perímetro: " +
		 * perimetroTrianguloRectangulo);
		 */

		// Quiero un codigo que me cargue una lista de 20 figuras al azar
		List<Figura> dibujo = new ArrayList<Figura>();
		Random generadorRandom = new Random();

		for (int i = 0; i < 20; i++) {
			int random = generadorRandom.nextInt(1, 4);
			switch (random) {
			case 1:
				Rectangulo rectangulo = new Rectangulo(generadorRandom.nextDouble(1, 100), 
						generadorRandom.nextDouble(1, 100));
				dibujo.add(rectangulo);
				break;
			case 2:
				TrianguloRectangulo triangulo = new TrianguloRectangulo(generadorRandom.nextDouble(1, 100), 
						generadorRandom.nextDouble(1, 100));
				dibujo.add(triangulo);
				break;
			case 3:
				Circulo circulo = new Circulo(generadorRandom.nextDouble(1, 100));
				dibujo.add(circulo);
				break;
			}
		}
		
		
		//Quiero la figura con mayor area
		Figura conMayorArea = dibujo.stream().max(Comparator.comparingDouble(f -> f.calcularArea())).orElse(null);
		System.out.println(MessageFormat.format("{0} de area {1}", 
				conMayorArea.getClass().getSimpleName(), 
				conMayorArea.calcularArea()));
		
		//Quiero la figura con menor perimetro
		Figura conMenorPerimetro= dibujo.stream().min(Comparator.comparingDouble(f -> f.calcularPerimetro())).orElse(null);
		System.out.println(MessageFormat.format("{0} de area {1}", 
				conMenorPerimetro.getClass().getSimpleName(), 
				conMenorPerimetro.calcularArea()));
		
		//una refencia a una intefaz  puede apuntar a cualquier objeto de las clases que la implementan
		AreaCalculable a = new Rectangulo(10,10);
		a = new Terreno(122,200);
	}

}
