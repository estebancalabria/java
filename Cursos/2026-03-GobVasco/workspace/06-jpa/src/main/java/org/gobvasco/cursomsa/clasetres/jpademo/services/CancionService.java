package org.gobvasco.cursomsa.clasetres.jpademo.services;

import java.util.ArrayList;
import java.util.List;

import org.gobvasco.cursomsa.clasetres.jpademo.dto.CancionDTO;
import org.gobvasco.cursomsa.clasetres.jpademo.entities.*;
import org.gobvasco.cursomsa.clasetres.jpademo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CancionService {
	
	@Autowired
	private CancionRepository repoCancion;
	
	@Autowired
	private ArtistaRepository repoArtista;
	
	public CancionDTO crearConError(CancionDTO cancion) {
		//En este ejemplo va a guardar el artista pero no va a guardar la cancion
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		//Voy a guardar la cancion
		if (true) {
			throw new RuntimeException("Error simulado");
		}
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}
	
	@Transactional
	public CancionDTO crearConErrorYTransaccion(CancionDTO cancion) {
		//En este ejemplo como da error hace ROOLBACK de la transaccion y no guarda el artista
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		//Voy a guardar la cancion
		if (true) {
			throw new RuntimeException("Error simulado");
		}
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}
	
	@Transactional
	public CancionDTO crearSinError(CancionDTO cancion) {
		//En este ejemplo como da error hace ROOLBACK de la transaccion y no guarda el artista
		
		Artista artista = new Artista();
		artista.setNombre(cancion.getArtista());
		
		this.repoArtista.save(artista);
		
		Cancion cancionNueva = new Cancion();
		cancionNueva.setArtista(artista);
		cancionNueva.setTitulo(cancion.getTitulo());
		
		this.repoCancion.save(cancionNueva);
		
		cancion.setId(cancionNueva.getId());
		
		return cancion;
	}


	public List<CancionDTO> listar(){
		List<Cancion> canciones = this.repoCancion.findAll();
		
		//Se puede usar MapStruct para noh acer el mapeo a mano
		List<CancionDTO> result = new ArrayList<>();
		
		for (Cancion c : canciones) {
			CancionDTO nuevo = new CancionDTO();
			nuevo.setId(c.getId());
			nuevo.setTitulo(c.getTitulo());
			
			if (c.getArtista() != null) {
				nuevo.setArtista(c.getArtista().getNombre());
			}
			
			 result.add(nuevo);
		}
		
		return result;		
	}
}
