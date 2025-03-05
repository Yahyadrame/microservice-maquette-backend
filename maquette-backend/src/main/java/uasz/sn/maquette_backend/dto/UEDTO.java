package uasz.sn.maquette_backend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UEDTO {
    private Long id;
    private String libelle;
    private String code;
    private int credit;
    private int coefficient;
    private Date dateCreation;
    private boolean actif;
    private boolean archive;
    private Long utilisateurId; // Référence à l'utilisateur
    private List<Long> ecsIds; // Liste des IDs des ECs associés
    private List<ECDTO> ecs; // Liste des ECs associés à l'UE
}