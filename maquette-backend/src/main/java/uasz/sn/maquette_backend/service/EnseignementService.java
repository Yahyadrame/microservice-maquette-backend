package uasz.sn.maquette_backend.service;


import uasz.sn.maquette_backend.dto.EnseignementDTO;

import java.util.List;

public interface EnseignementService {
    EnseignementDTO ajouterEnseignement(EnseignementDTO enseignementDTO);
    EnseignementDTO modifierEnseignement(Long id, EnseignementDTO enseignementDTO);
    void supprimerEnseignement(Long id);
    EnseignementDTO rechercherEnseignement(Long id);
    List<EnseignementDTO> listerEnseignements();
    void validerEnseignement(Long id);
}