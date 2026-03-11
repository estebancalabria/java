package org.indra.models;

public abstract class Animal {
   public abstract String getSonido();
   public void mostar(IDibujador dibu) {
	   dibu.mostar(this);
   }
}
