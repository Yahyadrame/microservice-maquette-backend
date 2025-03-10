package uasz.sn.maquette_backend.dto;

import lombok.Data;

@Data
public class EnseignementDTO {
    private Long id;
    private Long ecId;
    private String type; // CM, TD, TP
    private Long classeId;
    private String semestres;
    private Long formationId;
    private String formationNom; // Ajouter un champ pour le nom de la formation
    private String ecLibelle;    // Libellé de l'EC
    private String classeNom;    // Nom de la classe
}