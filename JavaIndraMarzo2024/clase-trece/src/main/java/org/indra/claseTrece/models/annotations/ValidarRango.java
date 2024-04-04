package org.indra.claseTrece.models.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidarRango {
    String mensaje() default "El atributo está fuera de rango";
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
}
