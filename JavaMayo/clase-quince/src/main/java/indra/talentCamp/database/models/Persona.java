package indra.talentCamp.database.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Persona {
	
	@Getter @Setter
	private int id;
	
	@Getter @Setter 
	private String nombre;
	
	@Getter @Setter
	private int edad;
		
	/*@Override
	public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
	}*/
}
