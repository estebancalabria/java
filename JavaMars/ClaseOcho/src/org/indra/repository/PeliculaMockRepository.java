package org.indra.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import org.indra.models.Pelicula;

public class PeliculaMockRepository implements IPeliculaRepository {

	private List<Pelicula> peliculas = new ArrayList<Pelicula>();
	
	public PeliculaMockRepository() throws Exception {
		this.add(new Pelicula("La momia",160,LocalDate.of(1996, Month.JUNE, 2),"Sam Reimi"));
		this.add(new Pelicula("Dr Strange",200,LocalDate.of(2022, Month.MAY, 2),"Sam Reimi"));
		this.add(new Pelicula("Avatar",220,LocalDate.of(2009, Month.MAY, 2),"James Cameron"));
	}
	
	@Override
	public List<Pelicula> findAll() throws Exception {
		return this.peliculas;
	}

	@Override
	public Pelicula findById(int id) throws Exception {
		//Vamos a asumir que hay uno
		return peliculas.stream().filter(p -> p.getId()==id ).findFirst().get();
	}

	@Override
	public Pelicula findByName(String name) throws Exception {
		return peliculas.stream().filter((Pelicula p) -> p.getTitulo().equals(name) ).findFirst().get();
	}

	@Override
	public void add(Pelicula objeto) throws Exception {
		this.peliculas.add(objeto);
		objeto.setId(this.peliculas.stream().mapToInt(p -> p.getId()).max().getAsInt() + 1);
	}

	@Override
	public void delete(int id) throws Exception {
		int index = this.peliculas.indexOf(this.peliculas.stream().filter( p -> p.getId() == id).findFirst().get());
        this.peliculas.remove(index);
        
        //Pelicula p = this.peliculas.stream().filter( p -> p.getId() == id).findFirst().get();
        //this.peliculas.remove(p);
	}

	@Override
	public void update(Pelicula objeto) throws Exception {
		this.delete(objeto.getId());
		this.peliculas.add(objeto);
	}
}
