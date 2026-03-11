package org.talentCamp.claseDos.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmpiezaConMayusculaValidator implements ConstraintValidator<EmpiezaConMayuscula,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext ctxt) {
        //ctxt.
        return ((s!= null) && s.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$"));
    }
}
