package org.indra.claseTrece.models.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidarRequerido {
	String mensaje() default "El atributo es requerido y esta vacio";
}

//Otra opcion utilizando value
/*
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidarRequerido {
	String value() default "El atributo es requerido y esta vacio";
}

//@ValidarRequerido("La accion debe tener simbolo")
*/