package org.talentCamp.claseDos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Pelicula extends ObjetoDeNegocio {

    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String genero;

}
