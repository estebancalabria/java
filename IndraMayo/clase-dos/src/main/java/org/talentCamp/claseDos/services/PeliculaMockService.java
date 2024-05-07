package org.talentCamp.claseDos.services;

import org.springframework.stereotype.Service;
import org.talentCamp.claseDos.models.Pelicula;

import java.util.*;

@Service
public class PeliculaMockService implements PeliculaService {

    List<Pelicula> peliculas;

    public  PeliculaMockService(){
        this.peliculas = new ArrayList<>();

        Pelicula losChicosDelCoro = new Pelicula("Drama", "Los Chicos del Coro");
        losChicosDelCoro.setId(1);

        Pelicula torrente = new Pelicula("Comedia", "Torrente");
        torrente.setId(2);

        Pelicula nueveReinas = new Pelicula("Thriller", "9 Reinas");
        nueveReinas.setId(3);

        Pelicula interstellar = new Pelicula("Sci-Fi", "Interstelllar");
        interstellar.setId(4);

        this.peliculas.add(losChicosDelCoro);
        this.peliculas.add(torrente);
        this.peliculas.add(nueveReinas);
        this.peliculas.add(interstellar);
    }

    @Override
    public List<Pelicula> obtenerTodas() {
        return this.peliculas;
    }

    @Override
    public Pelicula obtenerPorId(int id) {
        //Version labbda
        return this.peliculas.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();

        /*for (Pelicula p : this.peliculas){
            if (p.getId()==id){
                return p;
            }
        }
        throw new Error("Pelicula no encontrada");*/
    }

    @Override
    public void registrarPelicula(Pelicula pelicula) {
        if (pelicula.getId() != 0){
            throw new Error("La pelicula ya existe");
        }

        //Busco el id mas grande de la lista
        int maxId = this.peliculas.stream().mapToInt(p -> p.getId()).max().orElse(0);
        System.out.println("M" + maxId);
        //Con un for seria asi
        /*int maxId = 0;
        for (Pelicula p : this.peliculas){
            if (p.getId()>maxId){
                maxId=p.getId();
            }
        }*/

        pelicula.setId(maxId + 1);
        this.peliculas.add(pelicula);
    }

    @Override
    public void borrarPelicula(int id) {
        Pelicula pelicula = this.obtenerPorId(id);
        this.peliculas.remove(pelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        Pelicula p = this.obtenerPorId(pelicula.getId());
        p.setGenero(pelicula.getGenero());
        p.setNombre(pelicula.getNombre());
    }
}
