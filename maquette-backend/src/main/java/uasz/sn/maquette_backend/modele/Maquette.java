package uasz.sn.maquette_backend.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maquette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Nom de la maquette

    @Column(nullable = false, unique = true)
    private String semestres;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe; // Relation avec la classe

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation; // Relation avec la formation

    private boolean actif = true; // Par défaut, actif
    private boolean archive = false; // Par défaut, non archivé

    @ManyToMany
    @JoinTable(
            name = "maquette_ue",
            joinColumns = @JoinColumn(name = "maquette_id"),
            inverseJoinColumns = @JoinColumn(name = "ue_id")
    )
    private List<UE> ues; // Liste des UEs associées à cette maquette
}