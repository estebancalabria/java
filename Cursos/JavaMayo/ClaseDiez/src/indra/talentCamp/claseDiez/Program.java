package indra.talentCamp.claseDiez;

import java.time.*;
import java.util.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Manejo de Fechas
		LocalDate fecha = LocalDate.now();
		System.out.println(fecha);
		
		LocalDateTime fechaYHora = LocalDateTime.now();
		System.out.println(fechaYHora);
		
		LocalTime horaActual = LocalTime.now();
		System.out.println(horaActual);
		
		//LocalDate fechaEspecifica = LocalDate.of(2023, Month.FEBRUARY, 10);
		LocalDate cumpleBorja = LocalDate.of(2023, Month.DECEMBER, 24);
		System.out.format("Feliz cumple Borja el %d del mes %d", 
				cumpleBorja.getDayOfMonth(), 
				cumpleBorja.getMonthValue());
		System.out.println();
		
		LocalDate navidad = cumpleBorja.plusDays(1);
		System.out.println("Feliz Navidad");
		System.out.println(navidad);
		
		if (cumpleBorja.isBefore(navidad)) {
			System.out.println("Borja cunple antes de la navidad");
		}
		
		Period periodo = Period.between(cumpleBorja, navidad);
		System.out.format("Hay %d años, %d meses, %d dias entre el cumple de Borja y la navidad",
				periodo.getYears(), periodo.getMonths(), periodo.getDays());
		System.out.println();
		
		Duration duracion = Duration.between(LocalTime.now(), LocalTime.now().plusHours(1));
		System.out.println(duracion.getSeconds());
		
		//Manejo de Strings
		String texto = "Hola que tal como estan todos";
		List<String> palabras = Arrays.asList(texto.split(" "));
		palabras.forEach( System.out::println );
		String textoAMinuscula = texto.toLowerCase();
		System.out.println(textoAMinuscula);
		texto = "    dsdssdsdsd   ".trim(); //Quita los espacios adelante y atras
		
		//Diccionarios
		Map<String, String> diccionario = new HashMap<>();
		diccionario.put("casa", "Lugar donde vive la gente");
		if (!diccionario.containsKey("auto")) {
			diccionario.put("auto", "vehiculo que te lleva de un lado a otro");
		}
		
		for (var entrada : diccionario.entrySet()) {
			System.out.println(entrada.getKey() + " : " + entrada.getValue());
		}
		
		//Ejemplo de SET
		Set<String> conjunto = new HashSet<>();
		conjunto.add("Casa");
		conjunto.add("Casa");
		conjunto.add("Casa");
		conjunto.add("Mesa");
		conjunto.add("Mesa");
		conjunto.add("Mesa");
		
		System.out.println(conjunto.size());
		for (var e : conjunto) {
			System.out.println(e);
		}
		
		//Priority Queue. Elemento mas bajo es el que tiene mayot prioridad
		Queue<Integer> prioridades = new PriorityQueue<>();
		prioridades.offer(9);
		prioridades.offer(19);
		prioridades.offer(49);
		prioridades.offer(2);
		prioridades.offer(4);
		System.out.println("Valor Mas Prioritario :" + prioridades.poll());
		prioridades.offer(1);
		System.out.println("Valor Mas Prioritario :" + prioridades.poll());
		
	}

}
