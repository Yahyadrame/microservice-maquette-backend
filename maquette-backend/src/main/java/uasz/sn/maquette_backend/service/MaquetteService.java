package uasz.sn.maquette_backend.service;

import uasz.sn.maquette_backend.dto.MaquetteDTO;

import java.util.List;

public interface MaquetteService {
    MaquetteDTO ajouterMaquette(MaquetteDTO maquetteDTO);
    MaquetteDTO modifierMaquette(Long id, MaquetteDTO maquetteDTO);
    void supprimerMaquette(Long id);
    MaquetteDTO rechercherMaquette(Long id);
    List<MaquetteDTO> listerMaquettes();
    void activerOuDesactiverMaquette(Long id);
    void archiverOuDesarchiverMaquette(Long id);
}