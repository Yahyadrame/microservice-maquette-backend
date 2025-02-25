package uasz.sn.maquette_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class MaquetteDTO {
    private Long id;
    private String nom;
    private String semestres;
    private Long classeId; // Référence à la classe
    private Long formationId; // Référence à la formation
    private boolean actif;
    private boolean archive;
    private List<Long> uesIds; // Liste des IDs des UEs associées
}