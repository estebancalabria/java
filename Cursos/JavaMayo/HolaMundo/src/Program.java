
public class Program {

	public static void main(String[] args) {
		System.out.println("Hola Mundo");
		
		//Tipos De Datos Primitivos
		int numero = 20;
		System.out.println(numero);
		
		double medio = 0.5;
		System.out.println(medio);
		
		boolean verdadero = true;
		System.out.println(verdadero);
		
		//Tipos de Datos por referencia (hay que pedir memoria con new)
		String nombre = "Esteban"; //Como es tan comun se omite new
		//String nombre = new String("Hola");
		System.out.println(nombre);
		
		@SuppressWarnings("removal")
		Integer num = new Integer(2);  //Me tira 
		System.out.println(num);
		
		//Conversion de tipos
		//numero = medio; No compila, asignacion invalida (Type missmatch)
		
		medio = numero; //Conversion implicita
		
		String enLetras =  Integer.toString(numero); //Conversion explicita (especie casteo)
		System.out.println("El numero en letras es " + enLetras);
		System.out.printf("El numero en letras es %s", enLetras);
		System.out.println();
		System.out.printf("El numero en letras es %d", numero);
		System.out.println();
		
		enLetras = "22";
		numero = Integer.parseInt(enLetras);   //Conversion explicita de string a int                           
		System.out.println(enLetras);
		
		//Captura de excepciones
		try {
			enLetras = "22dos";
			numero = Integer.parseInt(enLetras);
		} catch (NumberFormatException ex) {
		//} catch (Exception ex) {
			System.out.println("No se pudo hacer la conversion de " + enLetras  + "a int");
		} catch (Exception ex) {
			System.out.println("Ocurrio un error inesperado");
		}

		//Convierto un string con un numero decimal en una variable float
		//Se animan uds?
		//	Double.
		enLetras = "22.2";
        double doubleLetras = Double.valueOf(enLetras);
        System.out.println(doubleLetras);
	}

}
