package org.indra.gaming;

import java.util.Random;
import org.indra.io.Console;

public class JuegoAdivinanzas {
	
	private static final int CANTIDAD_OPORTUNIDADES = 5;
	private static final String MENSAJE_NUMERO_MENOR = "El numero es menor al sugerido.";

	public void jugar() {
		
		Random generadorNumeros = new Random();
		int contador = CANTIDAD_OPORTUNIDADES;
		int valor = generadorNumeros.nextInt(0, 100);
		boolean adivinado = false;
		int intentar;
		do {
			intentar = Console.promptInt("Intenta adiviniar el numero: ");
			if (intentar == valor) {
				adivinado = true;
			} else if (intentar > valor) {
				System.out.println(MENSAJE_NUMERO_MENOR);
			} else if (intentar < valor) {
				System.out.println("El numero es mayor al sugerido.");
			}
			--contador;
		} while ((adivinado == false) && (contador > 0));
		
		if (adivinado) {
			System.out.println("Numero acertado.");
		} else {
			System.out.println("Numero no acertado. El numero era " + valor);
		}

	}
}
