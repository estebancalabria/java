package org.talentCamp.claseUnoSpringData.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Artists")
@ToString
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "artist")
    @NotBlank(message = "El nombre del artista no puede estar vacío")
    @Size(min = 1,max = 100,message = "El nombre del artista debe tener entre 1 y 100 caracteres")
    private String nombre;
}
