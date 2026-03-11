package org.talentCamp.claseUnoSpringData.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Songs",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title","artist"}))
@ToString
public class Cancion {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "title", nullable = false/*, unique = true*/)
    private String titulo;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="artists_id")
    private Artista artista;

    @Getter
    @Setter
    @Column(name = "releaseDate", nullable = false)
    private LocalDate fecha;

    @Getter
    @Setter
    @Column(name = "length", nullable = false)
    private int duracionSegundos;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion") //Me voy a ahorrar el repo
    //@JoinColumn(name = "songs_id")
    private List<Puntuacion> puntuaciones = new ArrayList<>();

    public  String getPuntuacionAsString(){
        String result = "Sin clasificar";

        if (this.puntuaciones.size() > 0) {
            double promedio = this.puntuaciones.stream().mapToInt(p -> p.getValor() ).average().orElseThrow();
            result = String.valueOf( Math.round(promedio) );
        }

        return result;
    }

}
