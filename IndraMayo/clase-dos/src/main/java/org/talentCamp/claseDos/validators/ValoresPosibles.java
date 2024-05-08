package org.talentCamp.claseDos.validators;
    
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValoresPosiblesValidator.class)
@Target({ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ValoresPosibles {
    String[] value() default {};
    String message() default "El campo no corresponde a ninguno de los valores posibles";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
