package org.talentCamp.claseDos.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmpiezaConMayusculaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmpiezaConMayuscula {
    String message() default "Debe Empezar con Mayuscula";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
