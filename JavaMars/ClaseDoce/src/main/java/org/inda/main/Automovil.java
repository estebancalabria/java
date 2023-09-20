package org.inda.main;

import lombok.*;

@Getter 
@Setter
@ToString
@RequiredArgsConstructor
public class Automovil {
    private int id;
    private @NonNull String marca;
    private @NonNull String modelo;
    private int kilometros;
}
