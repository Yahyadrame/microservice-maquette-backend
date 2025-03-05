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
}