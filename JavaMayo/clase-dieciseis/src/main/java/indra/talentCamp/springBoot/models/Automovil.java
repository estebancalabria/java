package indra.talentCamp.springBoot.models;

import lombok.*;

@Getter 
public class Automovil {
   @Setter
   private int id;
   @Setter
   private String marca;
   
   private String matricula;
   @Setter
   private boolean electrico;
   
   public void setMatricula(String matricula) {
	   if (!matricula.matches("^[0-9]{4}[A-Z]{2}$")) {
		   throw new Error("Validation Error : La matricula no tiene 4 numeros y 2 letras");
	   }
	   
	   this.matricula = matricula;
   }
   
}
