package org.talentCamp.claseDos.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.talentCamp.claseDos.models.Pelicula;
import org.talentCamp.claseDos.services.NotFoundServiceException;
import org.talentCamp.claseDos.services.PeliculaService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PeliculaController {

    @Autowired  //Inyeccion de dependencia por atributo / setter
    public PeliculaService service;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/api/pelicula")
    public Pelicula add(@Valid @RequestBody() Pelicula pelicula){
        System.out.println(pelicula.getNombre());

        //Esta validacion la mandamos al objeto de negocio
        /*if ((pelicula.getNombre()==null) || (pelicula.getNombre().trim().length()==0)){
            throw new Error("Falta el nombre");
        }*/

        this.service.registrarPelicula(pelicula);
        //System.out.println(pelicula.getId());
        return pelicula;
    }

    @GetMapping("/api/pelicula")
    @CrossOrigin("http://localhost:4200")
    public List<Pelicula> todas(){
        return this.service.obtenerTodas();
    }

    @DeleteMapping("/api/pelicula/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) throws NotFoundServiceException {
       // try{
            this.service.borrarPelicula(id);
            System.out.println("Existe" +  id);
            return ResponseEntity.ok(true);
        /*} catch (Exception e) {
            System.out.println("No existe" + id);
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    }

    //Probemos el ControllerAdvice
    /*@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  ResponseEntity<String> handleElementoNoEncontrado(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
    }*/
}
