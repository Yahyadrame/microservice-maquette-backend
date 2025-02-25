package uasz.sn.maquette_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class FormationDTO {
    private Long id;
    private String nom;
    private int duree;
    private String description;
    private String niveau;
    private boolean actif;
    private boolean archive;
    private List<Long> classesIds; // Liste des IDs des classes associées
    private List<Long> maquettesIds; // Liste des IDs des maquettes associées
}