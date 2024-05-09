package org.talentCamp.claseUnoSpringData.dto;

import lombok.Data;

//Ejemplo Bien Jorge: En la base de datos tengo una fecha de creacion y no quiero exponerla
//Quiero que la interfaz se llamen distinto
//Es como quiero hacer visible el mmodelo a la interfaz grafica

@Data
public class CancionDTO {
    private int id;
    private String nombre;
    private String compositor;
    private int duracion;
    private int año;
}
