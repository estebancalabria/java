package org.talentCamp.claseUnoSpringData.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

//Ejemplo Bien Jorge: En la base de datos tengo una fecha de creacion y no quiero exponerla
//Quiero que la interfaz se llamen distinto
//Es como quiero hacer visible el mmodelo a la interfaz grafica

@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class CancionDTO extends Dto{

    private int id;

    @NotBlank(message = "El nombre no puede quedar vacio")
    @Size(min = 5, max = 100, message = "El nombre tiene que tener entre 5 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El compositor no puede quedar vacio")
    private String compositor;

    @Max(value = 5400, message = "La cancion no puede durar mas de 90 min")
    private int duracion;

    @PastOrPresent
    private LocalDate año;
}
