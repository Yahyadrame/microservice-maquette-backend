package uasz.sn.maquette_backend.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String libelle;
    private String code;
    private int credit;
    private int coefficient;
    private Date dateCreation;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "ue")
    private Collection<EC> ecs;
}