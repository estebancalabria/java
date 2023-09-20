
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import indra.utils.Consola;

public class Program {

	public static void ejemploBufferedReaderConThrows() throws IOException{
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
		
		//try {
		try {
			System.out.println("Ingrese su nombre:");
			String nombre = reader.readLine();
			
			//Agregar que le pregunte tambien la edad y 
			//hacer una conversion de datos
			System.out.println("Ingrese su edad");
			String edadAsString = reader.readLine();
			int edad = Integer.parseInt(edadAsString); //Para repasar la conversion 
			
			System.out.println("Hola " + nombre + " de " + edad + " edad");
			
			
		//} catch (IOException e) {
		//	System.err.println(e.getMessage());
		//}
		} finally {
			System.out.println("Cerrando el BufferedReader");
			reader.close();
		}		
	}
	
	public static void ejemploBufferedReaderSinThrows(){
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
		
		try {
			try {
				System.out.println("Ingrese su nombre:");
				String nombre = reader.readLine();
				
				//Agregar que le pregunte tambien la edad y 
				//hacer una conversion de datos
				System.out.println("Ingrese su edad");
				String edadAsString = reader.readLine();
				int edad = Integer.parseInt(edadAsString); //Para repasar la conversion 
				
				System.out.println("Hola " + nombre + " de " + edad + " edad");			
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} finally {
			System.out.println("Cerrando el BufferedReader");
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}		
	}
	
	public static void ejemploScanner() {
		try (Scanner in = new Scanner(System.in)) {  //Es lo mismo que hacer un try..finally con un close
			System.out.println("Su nombre es...");
			String nombre = in.nextLine();
			System.out.println("Su edad es...");
			int edad = in.nextInt();
			System.out.println("Hola " +  nombre + " de " + edad + " años");
		} //solito hace un close
		
	}
	

	static final int CANTIDAD_OPORTUNIDADES = 5;
	static final int MAX_VALUE = 100;
	public static void juegoAdivinanzas() {
		Random r = new Random();
		int secreto = r.nextInt(1, MAX_VALUE);
		boolean adivino = false;
		int turnoActual = 1;
		
		System.out.println("Bienvenidos al juego. Tiene 5 oportunidades para adivinar el numero secreto");
		
		while ((!adivino) && (turnoActual<=CANTIDAD_OPORTUNIDADES)) {
			int adivinanza = Consola.readInt(turnoActual + ".Ingrese un numero");
			
			if (adivinanza<secreto) {
				System.out.println("El numero ingresado es menor al secreto");
			} else if (adivinanza>secreto) {
				System.out.println("El numero ingresado es mayor al secreto");
			} else {
				System.out.println("Felicitaciones has adivinado!");
				adivino = true;
			}
			turnoActual++;
		}
		
		if (!adivino) {
			System.err.println("Lo siento no has adivinado. El numero secreto era " + secreto);
		}		
	}
	
	public static void main(String[] args) {
		//El operador para el resto de una division es %
		//Este hacerlo con un for...
		//Dado un numero... me diga si es primo o no...
		int numero = Consola.readInt("Ingrese un numero y diremos si es primo");
		boolean esPrimo = true;
		for (int i=2; i<numero/2; i++) {
			if ((numero % i)==0) {
				esPrimo = false;
				break; //EL lado oscuro de la programacion!
			}
		}
		if (esPrimo) {
			System.out.println("El numero es Primo");
		}else {
			System.out.println("El numero no es primo");
		}
	}

}
