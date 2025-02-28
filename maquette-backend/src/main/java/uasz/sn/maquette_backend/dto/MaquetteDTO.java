package uasz.sn.maquette_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class MaquetteDTO {
    private Long id;
    private String nom;
    private String semestres;
    private Long classeId; // Référence à la classe
    private String classeNom; // Nom de la classe
    private Long formationId; // Référence à la formation
    private String formationNom; // Nom de la formation
    private boolean actif;
    private boolean archive;
    private List<Long> uesIds; // Liste des IDs des UEs associées
    private List<String> uesNoms; // Liste des noms des UEs associées
    private List<UEDTO> ues; // Liste des UEs avec leurs ECs
}