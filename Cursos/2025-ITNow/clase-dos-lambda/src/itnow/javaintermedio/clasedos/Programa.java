package itnow.javaintermedio.clasedos;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenidos al mundo de las interfaces funcionales");
		
		//Ejemplo 1: Uso convencional de una interfaz
		System.out.println("EJ 1 : Uso habitual de una interfaz");
		System.out.println("-----------------------------------");
		OperacionBinariaInteger sumador = new SumarInteger();
		System.out.println("La suma de 2 + 2 es " + sumador.operar(2, 2));
		System.out.println("");
		
		//Ejemplo 2: Si quiero hacer una resta....
		//           Tedria que agregar la clase RestarInteger
		//           Pero me da pereza... entonces la creo al momento...
		System.out.println("EJ 2 : Uso de interfaces funcionales con expresiones lambda");
		System.out.println("-----------------------------------------------------------");
		OperacionBinariaInteger restador = (a,b) -> a-b;              //Sin LLaves el return esta implicito
		//OperacionBinariaInteger restador = (a,b) -> { return a - b; };
		//OperacionBinariaInteger restador = (int a, int b) -> (a - b);
		System.out.println("La resta de 5 - 3  es " + restador.operar(5, 3));
		System.out.println("");
		
		//Ejemplo 3: Uso de un metodo que ya existe en otro lado
		System.out.println("EJ 3 : Uso de interfaces con metodos estaticos que ya existe");
		System.out.println("------------------------------------------------------------");
		OperacionBinariaInteger multiplicador = MultiplicarInteger::multiplyStatic;  //Uso del operador :: (method reference)
		System.out.println("La mutiplicacion de 5 * 5   es " + multiplicador.operar(5, 5));
		System.out.println("");
		
		
		System.out.println("EJ 4 : Uso de interfaces con metodos de instancia que ya existe");
		System.out.println("---------------------------------------------------------------");
		MultiplicarInteger instanciaMultiplicar = new MultiplicarInteger();
		OperacionBinariaInteger multiplicadorInstancia = instanciaMultiplicar::mult;  //Uso del operador :: (method reference)
		System.out.println("La mutiplicacion de 6 * 6   es " + multiplicadorInstancia.operar(6, 6));
		//System.out.println("La mutiplicacion de 6 * 6   es " + instanciaMultiplicar.mult(6, 6)); es lo mismo que eso
		System.out.println("");
		
		
		System.out.println("EJ 5 : Uso de funcionales con genericos");
		System.out.println("---------------------------------------");
		OperacionBinaria<Integer, Integer> divisorEneros = (a, b) -> a/b;   // OJO: El operador / es division entera
		System.out.println("La division de 6 / 2 es " + divisorEneros.operar(6, 2));
		System.out.println("");
		
		System.out.println("EJ 6 : OJO CON LOS TIPOS DE DATOS");
		System.out.println("---------------------------------");
		OperacionBinaria<Double, Integer> divisor = (a, b) -> (double)a/ (double)b;   // OJO: El operador / es division entera
		System.out.println("La division de 6 / 4 es " + divisor.operar(6, 4));
		System.out.println("");
		
		//Interfaces funcionales que ya vienen con JAVA
		System.out.println("EJ 7 : Ya viene con java BinaryOperator<T>");
		System.out.println("-----------------------------------------");
		BinaryOperator<Integer> potencia = (a,b) -> (int)Math.round(Math.pow(a, b));
		System.out.println("La 2 elevado a la 3 es " + potencia.apply(2, 3));
		System.out.println("");
		
		//Interfaces funcionales que ya vienen con JAVA
		System.out.println("EJ 8 : Ya viene con java Function<T, R>");
		System.out.println("-----------------------------------------");
		Function<String, Integer> contarCaracteres = (str) -> str.length();
		System.out.println("La cantidad de caracteres de hola es " + contarCaracteres.apply("Hola"));
		System.out.println("");
		
		//Interfaces funcionales que ya vienen con JAVA
		System.out.println("EJ 9 : Ya viene con java Predicate<T>");
		System.out.println("-----------------------------------------");
		Predicate<Integer> esPar = (num) -> (num % 2) == 0;
		System.out.println("El numero 8 es par : " + esPar.test(8));
		System.out.println("");
		
	}

}
