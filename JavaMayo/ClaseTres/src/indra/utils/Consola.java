package indra.utils;

import java.util.Scanner;

public class Consola {
	private static boolean esNumerico(String numero) {
		//quiero que haga captura de excepciones
		//y me devuelva si el string ingresado es numerico
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
	
	public static int readInt() {
		//lea un entero de la entrada estandar
		//si el usuario ingresa otra cosa que no sea un numero
		//se lo vuelve a preguntar
		// while....
        String numero = null;
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

        do {
            System.out.println("Ingrese un numero");
            numero = in.nextLine();
        }while(!esNumerico(numero));
        
        return Integer.valueOf(numero);
	}
	
	public static int readInt(String prompt) {  //Sobrecarga
		// while....
        String numero = null;
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

        do {
            System.out.println(prompt);
            numero = in.nextLine();
        }while(!esNumerico(numero));
        
        return Integer.valueOf(numero);
	}

}
