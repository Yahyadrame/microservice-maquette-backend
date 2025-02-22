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
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "ec_id", nullable = false)
    private EC ec;

    @Column(nullable = false)
    private String type; // CM, TD, TP

    private boolean validationChef = false;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @Column(nullable = false)
    private String semestres;

    // Ajout de la méthode isValide()
    public boolean isValide() {
        return validationChef;
    }
}