package indra.talentCamp.layers.services;

import java.awt.Color;
import java.util.Random;

import indra.talentCamp.layers.models.Jedi;
import indra.talentCamp.layers.persistence.interfaces.Repository;
import indra.talentCamp.layers.persitence.JediMockRepository;
import indra.talentCamp.layers.persitence.SaveErrorException;

public class JediService {
	
	private Repository<Jedi> repository;
	
	public JediService(Repository<Jedi> repo) {
		//Dependencia fija, establecida en el constructor
		//this.repository = new JediMockRepository();	
		//this.repository = new JediFileRepository();

		//Que solucion se les ocurre para que el Servicio pueda trabajar indistintamente con
		//cualquier tipo de repositorio y si se cambia el repositorio concreto no haya que 
		//actualizar el codigo del servicio
		this.repository = repo;
	}
	

	private int calcularNivelFuersa(Jedi j) {
		//por ejemplo Se evalua al jedi y se obtiene su nivel de fuerza llamando a una api externa
		return new Random().nextInt(1000, 100000);
	}

	
	private Color crearSableNuevo() {
		//Imaginen que se crea un sable nuevo con su color correspondiete
		return Color.GREEN;
	}
	
	public void alistar(String nombre) {
	    Jedi nuevo = new Jedi(nombre, this.crearSableNuevo());
	    nuevo.setNivelDeFuerza( this.calcularNivelFuersa(nuevo) );
	    try {
			this.repository.save(nuevo);
		} catch (SaveErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
