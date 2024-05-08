package org.talentCamp.claseDos.services;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.talentCamp.claseDos.models.Pelicula;

import java.time.LocalDate;
import java.util.*;

@Service
public class PeliculaMockService implements PeliculaService {

    List<Pelicula> peliculas;

    public PeliculaMockService() {
        this.peliculas = new ArrayList<>();

        //Pelicula losChicosDelCoro = new Pelicula( "Los Chicos del Coro","Drama", 10);
        Pelicula losChicosDelCoro = Pelicula.builder()
                .nombre("Los Chicos del Coro")
                .genero("Drama")
                .puntuacion(10)
                .fechaLanzamiento(LocalDate.of(2004, 5, 1))
                .build();
        losChicosDelCoro.setId(1);

        Pelicula torrente = Pelicula.builder()
                .nombre("Torrente")
                .genero("Comedia")
                .puntuacion(10)
                .fechaLanzamiento(LocalDate.of(1998, 3, 13))
                .build();
        torrente.setId(2);

        Pelicula nueveReinas = Pelicula.builder()
                .nombre("9 Reinas")
                .genero("Thriller")
                .puntuacion(8)
                .fechaLanzamiento(LocalDate.of(1998, 3, 13))
                .build();
        nueveReinas.setId(3);


        Pelicula interstellar = Pelicula.builder()
                .nombre("Interstellar")
                .genero("Sci-Fi")
                .puntuacion(7)
                .fechaLanzamiento(LocalDate.of(2014, 11, 7))
                .build();
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
    public Pelicula obtenerPorId(int id) throws NotFoundServiceException {
        //Version labbda

        //Esta opcion lanza un NoSuchElementException que es de Java
        /*return this.peliculas.stream().filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();*/

        //Utilizo una excepcion personalizada
        return this.peliculas.stream().filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundServiceException::new);

        /*for (Pelicula p : this.peliculas){
            if (p.getId()==id){
                return p;
            }
        }
        throw new Error("Pelicula no encontrada");*/
    }

    //El @Valid no me funciono en el servicio :(
    @Override
    public void registrarPelicula(Pelicula pelicula) {
        if (pelicula.getId() != 0) {
            throw new Error("La pelicula ya existe");
        }

        //Si es una excepcion Verificada, tengo que agregarle el throws al Servicio y al Controlador
        //SI no quiero eso o le pongo el SneakyThrows de lombock o uso excepciones no verificadas

        //Con el @Valid las validaciones se realizan en forma automatica al reibir el objeto
        //pelicula.validate();

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
    public void borrarPelicula(int id) throws NotFoundServiceException {
        Pelicula pelicula = this.obtenerPorId(id);
        this.peliculas.remove(pelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) throws NotFoundServiceException {
        Pelicula p = this.obtenerPorId(pelicula.getId());
        p.setGenero(pelicula.getGenero());
        p.setNombre(pelicula.getNombre());
    }
}
