package org.talentCamp.claseDos.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValoresPosiblesValidator implements ConstraintValidator<ValoresPosibles,String> {

    String[] valoresPosibles = null;

    @Override
    public void initialize(ValoresPosibles constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.valoresPosibles = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (String valorPosible : valoresPosibles){
            if (valorPosible.equals(s)){
                return true;
            }
        }
        return false;
    }
}
