import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Programa /*implmements Conversor*/{
	
	public static boolean esPar(int numero) {
		return (numero%2 == 0);
	}
	
	public static List<Integer> convertir(List<Integer> lista, Conversor c){
		return lista.stream().map(c::convertir).toList();
	}

	public static void main(String[] args) {
		List<Integer> lista = Arrays.asList(1,2,2, 3,4,4 ,5,6,6,7,8,10,11,12,13,14);
		System.out.println("Lista Original " +  lista);
		
		//Elementos pares
		List<Integer> pares = lista.stream().filter( n -> n%2 == 0 ).toList();
		//List<Integer> pares = lista.stream().filter(n -> {return n%2 == 0;} ).collect(Collectors.toList());
		//List<Integer> pares = lista.stream().filter( Programa::esPar ).toList();
		System.out.println("Pares " + pares);
		
		//Obtener el elemento mas grande
		//int max = lista.stream().max( (a,b) -> a-b ).orElse(0);
		//int max = lista.stream().max( (Integer a,Integer b) -> a-b ).orElse(0);
		int max = lista.stream().max(Integer::compare).orElse(0);
		System.out.println("El max : " + max);
		
		//La suma de todos los elementos
		//int suma = lista.stream().mapToInt(n -> n).sum();
		int suma = lista.stream().reduce(0, Integer::sum);
		//int suma = lista.stream().reduce(0, (a,b)-> a+b);
		System.out.println("La suma : " + suma);
		
		//Obtener una lista que sea la lista original elevada al cuadrao
		List<Integer> cuadrados =  lista.stream().map(n -> n * n).toList();
		System.out.println("Los cuadrados son : " +  cuadrados);
				
		//Contar la cantidad de elementos impares de la lista
        int contarImpares = lista.stream().filter(n -> n % 2 != 0).toList().size();
        //int contarImpares = (int) lista.stream().filter(n -> n % 2 != 0).count();
        System.out.println("Cantidad de impares : " + contarImpares);
        
        //Convertir la lista de enteros en una lista de String
        List<String> listaAsString = lista.stream().map(n -> n.toString()).toList(); // o n -> n.toString()
        System.out.println("Lista as String " + listaAsString);
        
        //Quiero la lista sin duplicados
        List<Integer> sinDuplicados = lista.stream().distinct().toList();
        System.out.println("Sin duplicados " +  sinDuplicados);
        

        //
        //Excelente ejemplo para ver el funcionamiento de las expresiones lambda
        //
        
		/*System.out.println(lista.stream().map(n->{
			System.out.println("mapeando:" + n);
			return n;
		}).filter(n->{
			System.out.println("filtrando:" + n);
			return n % 2 == 0;
		}).toList());*/

		
		
		/*System.out.println(lista.stream().map(n->{
			System.out.println("mapeando:" + n);
			return n;
		}).filter(n->{
			System.out.println("filtrando:" + n);
			return n % 2 == 0;
		}).findFirst());*/
        
        //Con Interfaz Funcional definida por el usuario (innecesario)
        //Conversor conversor = a -> a-1;
        //List<Integer> convertida = lista.stream().map( conversor::convertir ).toList();
        //System.out.println("La lista convertida " + convertida);
        
        //Utilizando una interfaz funcional definida en la libreria de java
        Function<Integer, Integer> function = a -> a-1;
        List<Integer> convertida = lista.stream().map( function ).toList();
        System.out.println("La lista convertida :" + convertida);
        
        List<Integer> conversion2 = Programa.convertir(lista, x->x*3 );
        System.out.println("La lista convertida 2: " + conversion2);
        
        //int a = 1;
        //int b = conversor.convertir(a);

	}

}
