package org.talentCamp.claseUnoSpringData.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Puntuacion")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(name = "puntuacion", nullable = false)
    private int valor;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "songs_id")
    private Cancion cancion;

    @Override
    public String toString() {
        return String.valueOf(this.valor);
    }
}
