package org.talentCamp.claseUnoSpringData.services;

public class ServiceValidationException extends RuntimeException {
    public ServiceValidationException(String message){
        super(message);
    }
}
