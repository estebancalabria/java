package org.talentCamp.claseDos.models;

import jakarta.validation.constraints.*;
import lombok.*;
import org.talentCamp.claseDos.validators.EmpiezaConMayuscula;
import org.talentCamp.claseDos.validators.ValoresPosibles;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula extends ObjetoDeNegocio {

    @Getter
    @Setter
    @NotBlank(message = "Falta el nombre de la pelicula")
    @EmpiezaConMayuscula(message = "Cada palabra del nombre de la pelicula tiene que empezar con mayuscula")
    private String nombre;

    @Getter
    @Setter
    @NotBlank(message = "Falta el genero de la pelicula")
    @Size(min = 4, max = 10, message = "El genero tiene que estar entre 4 y 10 caracacteres")
    @ValoresPosibles(value = {"Comedia","Sci-Fi","Thriller","Drama"}, message = "Solo se admiten Comedia, Sci-Fi, Thriller y Drama para el genero")
    private String genero;

    @Getter
    @Setter
    //@Positive(message = "La puntuacion debe ser mayor a 0")
    @Min(value = 1, message = "La puntuacion no puede ser menor a 1.")
    @Max(value=10, message = "La puntuacion no puede ser mayor a 10.")
    private int puntuacion;

    @Getter
    @Setter
    @PastOrPresent(message = "La fecha de lanzamiento no puede ser posterior a la fecha actual")
    private LocalDate fechaLanzamiento;

    @Override
    //Esto lo vamos a mejorar!
    public void validate() throws ModelValidationException {
        super.validate();

        //Voy a reemplazar estas anotaciones por validaciones para evitar el codigo BoirlerPlate
        /*if ((nombre==null) || (nombre.trim().isEmpty())){
            throw new ModelValidationException("El nombre de la pelicula no puede quedar vacio");
        }

        if ((genero==null) || (genero.trim().isEmpty())){
            throw new ModelValidationException("El genero de la pelicula no puede quedar vacio");
        }*/


    }

}
