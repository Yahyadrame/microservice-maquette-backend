package uasz.sn.maquette_backend.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "ue_id")
    private UE ue;
}