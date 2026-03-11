package org.indra.clasequince.dto;

import lombok.*;

@Getter 
@Setter
@RequiredArgsConstructor
public class Exito {
	private String mensaje = "Operacion Exitosa";
	@NonNull private Object resultado;	
}
