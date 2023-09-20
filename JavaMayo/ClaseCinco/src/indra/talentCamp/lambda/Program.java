package indra.talentCamp.lambda;


import java.util.*;
import java.util.stream.Collectors;

import indra.talentCamp.lambda.models.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String frase = "Que la fuerza " + "este contigo";
	
		//String frase = "Que la fuerza";
		//frase += " este";
		//frase += " contigo";
		
		//Utilizando la fuerza y la sabiduria jedi 
		//hacemos la concatenacionde String mas eficiente
		/*StringBuilder builder = new StringBuilder();
		builder.append("Que la fuerza");
		builder.append(" este");
		builder.append(" contigo");
		
		System.out.println(builder.toString());*/
		
		//List<Persona> jedis = new ArrayList<Persona>();
		//List<Persona> jedis = new LinkedList<Persona>();
		//jedis.add(new Persona("Anakin", 178));
		//jedis.add(new Persona("Yoda", 80));
		//jedis.add(new Persona("Asoka", 165));
		//jedis.add(new Persona("Luke", 165));
		
		List<Persona> jedis = Arrays.asList(
				new Persona("Anakin", 178),
				new Persona("Yoda", 80),
				new Persona("Asoka", 165),
				new Persona("Luke", 165));
		
		//System.out.println("La lista de Jedis:");
		
		//Iteraciones
		
		//Iterando con un foreach
		//for (Persona jedi : jedis) {
		//	System.out.println(jedi.getNombre());
		//}
		
		//Iterando con expresiones lambda
		//
		//  jedi -> System.out.println(jedi)
		//
		//  es lo mismo que hacer
		//
		//  void mostrar(Persona jedi){
		//       System.out.println(jedi)
		//  }
		//jedis.forEach( jedi -> System.out.println(jedi) );
		
		//Iterando con lamda de una manera particular... ::
		//jedis.forEach( System.out::println );
		
		//
		//Max
		//
		
		//List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		//Calcular el maximo de forma tradicional
		/*int max = 0;
        for(int numero: numeros) {
            //if(numero > max) {
        	if (Integer.compare(numero, max)>0) {
                max = numero;
            }
        }
        System.out.println("Max:" + max);*/
		
		//Maximo con expresion lambda
		//int max = numeros.stream().max( (a,b) -> a-b ).orElse(0);
		//int max = numeros.stream().max( (a,b) -> a.compareTo(b) ).orElse(0);
		//int max = numeros.stream().max( (a,b) -> Integer.compare(a, b) ).orElse(0);
		//System.out.println("Max:" + max);
		
		//El jedi mas alto con una expresion lambda
		//Persona jediMasAlto = jedis.stream().max( (a,b)-> a.getCentimetrosAltura() - b.getCentimetrosAltura()).orElse(null);
		//Persona jediMasAlto = jedis.stream().max( Comparator.comparingInt(Persona::getCentimetrosAltura)).orElse(null);
		//System.out.println(jediMasAlto);
		
		//
		//  Sumatoria
		//
		//List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		//Sumatoria convencional
		//int sumatorio = 0;
        //for(int num: numeros) {
        //    sumatorio += num;
        //}
        //System.out.println("Sumatorio:"+ sumatorio);
		
		//Sumatoria con expresion lambda
		//System.out.println("Sumatoria: " + numeros.stream().mapToInt(n->n).sum());
		
		//La Sumatoria de las altura de todos los jedis
		//System.out.println("Alturas :" + jedis.stream().mapToInt(jedi -> jedi.getCentimetrosAltura()).sum());
		//System.out.println("Alturas :" + jedis.stream().mapToInt(Persona::getCentimetrosAltura).sum());
		 
		//
		// Media....
		//
		//List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		//Media tradicional
		//int sumatorio = 0;
        //for(int num: numeros) {
        //    sumatorio += num;
        //}
        //double media = (double)sumatorio / (double)numeros.size();
        //System.out.println("Media:" + media);

		//Media con expresion lambda
		//System.out.format("Media %.2f" , numeros.stream().mapToDouble(n-> (double)n).average().orElse(0));
		//System.out.printf("Media %.2f" , numeros.stream().mapToDouble(n-> (double)n).average().orElse(0));
		
		//La media de las alturas de los jedis. Quien me lo pasa por el chat
		//System.out.println("media de las alturas:" + jedis.stream().mapToDouble(Persona::getCentimetrosAltura).average().orElse(0));
        
		//
        //Filtrar Elementos
		//
		//List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		//numeros.stream().filter(n -> n>100).forEach(System.out::println);
		//List<Integer> numerosMayores100 = numeros.stream().filter(n -> n>100).collect(Collectors.toList());
		//System.out.println(numerosMayores100);
		
		//Mostrar la lista de jedis que miden mas de un metro. Quien me lo pasa por chat??
		//List<Persona> jedisAltos = jedis.stream().filter(jedi -> jedi.getCentimetrosAltura() > 100).collect(Collectors.toList());
		//jedisAltos.forEach(System.out::println);
		
		//
		//Map
		//
		//List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		//numeros.stream().map( n -> n*2).forEach(System.out::println);
		//List<Integer> dobles = numeros.stream().map( n -> n*2).collect(Collectors.toList());
		
		//Convertir la lista de jedis en una lista de alturas(int). Luego mostrarla
		//Quien me lo pasa por chat?
		//List<Integer> alturas = jedis.stream().map( p -> p.getCentimetrosAltura()).collect(Collectors.toList());
		//alturas.forEach(System.out::println);
		
		
		//
		//Reduce
		//
		
		//Sumatoria con el reduce
		List<Integer> numeros = Arrays.asList(4,5,656,232,54,657,865,23,433);
		int sumatoria = numeros.stream().reduce( 0, (acumulado, valorActual) -> acumulado+valorActual);
		System.out.println(sumatoria);
		
	}

}
