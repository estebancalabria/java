package org.tiben;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Requerido {
    String mensaje() default "Este atributo es requerido";
}