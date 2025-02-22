package uasz.sn.maquette_backend.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Nom de la classe (ex : Classe A, Groupe 1)

    @Column(nullable = false)
    private int annee; // Année de la formation (ex : 1 pour L1, 2 pour L2)

    @Column(length = 500)
    private String semestres; // Description des semestres (ex : "Semestre 1, Semestre 2")

    @Column(length = 500)
    private String description; // Description de la classe (ex : caractéristiques spécifiques)

    private boolean actif = true; // Par défaut, la classe est active
    private boolean archive = false; // Par défaut, la classe n'est pas archivée

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation; // Formation associée à la classe

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Maquette> maquettes; // Liste des maquettes associées à la classe
}