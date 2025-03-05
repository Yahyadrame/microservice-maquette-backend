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
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Nom de la formation (ex : Licence Informatique)

    @Column(nullable = false)
    private int duree; // Durée de la formation en années (ex : 3 pour une licence)

    @Column(length = 500)
    private String description; // Description de la formation

    @Column(nullable = false)
    private String niveau; // Niveau de la formation (L1, L2, Master, etc.)

    private boolean actif = true; // Formation active par défaut
    private boolean archive = false; // Formation archivée (non active)

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Classe> classes; // Liste des classes associées à cette formation

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Maquette> maquettes; // Liste des maquettes associées à cette formation


    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Enseignement> enseignements;
}