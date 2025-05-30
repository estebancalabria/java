package itnow.javaIntermedio.clasesUno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {
		
		//Quiero crear una cuenta con 1000
		//Depositar 500
		//Depositar 200
		//Extraer 2000.
		//Luego de cada operacion mostar el salgo de la cuenta
			
		/*Scanner scanner = new Scanner(System.in);
		try {
			try {
				Cuenta cuenta = new Cuenta(1000);
				System.out.println(cuenta.getSaldo());
		
				System.out.println("Ingrese el monto a depositar");
				double montoDeposito = scanner.nextDouble();
				cuenta.depositar(montoDeposito);
				System.out.println(cuenta.getSaldo());
		
				System.out.println("Ingrese el monto a extraer");
				double montoExtraer = scanner.nextDouble();
				cuenta.extraer(montoExtraer);
				
				System.out.println("El saldo total es...");
				System.out.println(cuenta.getSaldo());
				
			//Opcion 1 : Discriminar los distintos tipos de excepciones
		    //           Desventaja: Cuando tengo demasiados tipos de excpeciones distintos
			}catch (SaldoInsuficienteException e) {
				System.out.println("Error: " + e.getMessage());
			}catch (InputMismatchException e) {
				System.out.println("Entrada incorrecta de datos");			
			}
				
			//Opcion 2 : Captutar un tipo de excepcion Generico
			/*} catch (Exception e) {
			    System.out.println("Error: " + e.getMessage());
			}*/
		/*}finally {
			scanner.close();
		}*/
		
		
		//Version 2
		try {
			Cuenta cuenta = new Cuenta(1000);
			System.out.println(cuenta.getSaldo());
	
			double montoDeposito = Consola.readDouble("Ingrese el monto a depositar");
			cuenta.depositar(montoDeposito);
			System.out.println(cuenta.getSaldo());
	
			double montoExtraer = Consola.readDouble("Ingrese el monto a extraer");
			cuenta.extraer(montoExtraer);
			
			System.out.println("El saldo total es...");
			System.out.println(cuenta.getSaldo());

		}catch (SaldoInsuficienteException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}
