package org.indra.clasequince.dto;

import lombok.*;

@Getter 
@Setter
@RequiredArgsConstructor
public class RespuestaError {
   private String mensaje = "Operacion Fallida";
   @NonNull private String detalle;
}
