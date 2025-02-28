package uasz.sn.maquette_backend.dto;

import lombok.Data;

@Data
public class EnseignementDTO {
    private Long id;
    private Long utilisateurId; // Référence à l'utilisateur
    private String utilisateurNom; // Nom de l'utilisateur
    private Long ecId; // Référence à l'EC
    private String ecNom; // Nom de l'EC
    private String type; // CM, TD, TP
    private boolean validationChef;
    private Long classeId; // Référence à la classe
    private String classeNom; // Nom de la classe
    private Long formationId; // Référence à la formation
    private String formationNom; // Nom de la formation
    private String semestres;
}