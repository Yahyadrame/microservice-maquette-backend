package uasz.sn.maquette_backend.modele;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Enseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ec_id", nullable = false)
    private EC ec;

    @Column(nullable = false)
    private String type; // CM, TD, TP


    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @Column(nullable = false)
    private String semestres;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;


}