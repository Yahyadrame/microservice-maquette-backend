package uasz.sn.maquette_backend.dto;


import lombok.Data;

import java.util.List;

@Data
public class ClasseDTO {
    private Long id;
    private String nom;
    private int annee;
    private String semestres;
    private String description;
    private boolean actif;
    private boolean archive;
    private Long formationId; // Référence à la formation
    private List<Long> maquettesIds; // Liste des IDs des maquettes associées
}
