package org.talentCamp.claseDos.models;

//Layer Supertype
//La clase base de todos los objetos de negocio
public abstract class ObjetoDeNegocio {

    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
