package uasz.sn.maquette_backend.dto;

import lombok.Data;

@Data
public class ECDTO {
    private Long id;
    private String libelle;
    private String code;
    private int cm;
    private int td;
    private int tp;
    private int tpe;
    private int vht;
    private int coefficient;
    private String description;
    private Long ueId; // Référence à l'UE
}