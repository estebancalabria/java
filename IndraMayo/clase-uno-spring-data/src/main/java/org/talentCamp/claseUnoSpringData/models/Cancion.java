package org.talentCamp.claseUnoSpringData.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Songs")
public class Cancion {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "title", nullable = false)
    private String titulo;

    @Getter
    @Setter
    @Column(name = "artist", nullable = false)
    private String artista;

    @Getter
    @Setter
    @Column(name = "releaseDate", nullable = false)
    private LocalDate fecha;

    @Getter
    @Setter
    @Column(name = "length", nullable = false)
    private int duracionSegundos;
}
