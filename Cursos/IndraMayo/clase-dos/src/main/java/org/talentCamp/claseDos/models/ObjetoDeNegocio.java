package org.talentCamp.claseDos.models;

import jakarta.validation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

//Layer Supertype
//La clase base de todos los objetos de negocio
public abstract class ObjetoDeNegocio {

    @Getter @Setter
    private int id = 0;

    public void validate() throws ModelValidationException{
        //Realizamos validaciones de negocios
        StringBuilder sb = new StringBuilder();
        //Chequeo las validaciones en forma programatica
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ObjetoDeNegocio>> validations =  validator.validate(this);
            for (ConstraintViolation<ObjetoDeNegocio> validation : validations){
                sb.append(validation.getMessage());
                sb.append("\n");
            }

            if (!sb.isEmpty()){
                throw new ModelValidationException(sb.toString());
            }
        }
    }
    
}
