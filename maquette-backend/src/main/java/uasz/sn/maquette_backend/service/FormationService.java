package uasz.sn.maquette_backend.service;

import uasz.sn.maquette_backend.dto.FormationDTO;

import java.util.List;

public interface FormationService {
    FormationDTO ajouterFormation(FormationDTO formationDTO);
    FormationDTO modifierFormation(Long id, FormationDTO formationDTO);
    void supprimerFormation(Long id);
    FormationDTO rechercherFormation(Long id);
    List<FormationDTO> listerFormations();
    void activerOuDesactiverFormation(Long id);
    void archiverOuDesarchiverFormation(Long id);
}