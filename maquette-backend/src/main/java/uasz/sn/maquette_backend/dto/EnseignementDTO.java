package uasz.sn.maquette_backend.dto;

import lombok.Data;

@Data
public class EnseignementDTO {
    private Long id;
    private Long utilisateurId; // Référence à l'utilisateur
    private Long ecId; // Référence à l'EC
    private String type; // CM, TD, TP
    private boolean validationChef;
    private Long classeId; // Référence à la classe
    private String semestres;
}