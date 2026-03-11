package indra.talentCamp.polimorfismo;

import java.util.*;
import indra.talentCamp.polimofismo.models.*;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jugador excalibur = new Guerrero("Excalibur");
		Guerrero batman = new Guerrero("Batman");
		Jugador thanos = new Mago("Thanos");
		
		List<Jugador> personajes = new ArrayList<Jugador>();
		personajes.add(excalibur);
		personajes.add(batman);
		personajes.add(thanos);
		
		//Conienzo del turno
		System.out.println("Comienzo del turno");
		personajes.forEach(System.out::println);
		System.out.println("-----------------");
		
		batman.accion(excalibur);
		thanos.accion(excalibur);
		
		//No quiero que desde afuera me modifiquen los stats de mi personaje
		//thanos.setHp(0);
		
		personajes.forEach(p -> p.finalizarTurno());

		//Fin del turno
		System.out.println("-----------------");
		System.out.println("Fin del turno. Los que quedaron vivos son:");
		//Mostrar los que quedaron vivos con expresiones lambda
		//personajes.forEach(System.out::println);
		personajes.stream().filter(n -> n.estaVivo()).forEach(System.out::println);
		
	}

}
