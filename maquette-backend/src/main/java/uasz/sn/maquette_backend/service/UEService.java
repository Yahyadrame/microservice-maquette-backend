package uasz.sn.maquette_backend.service;

import uasz.sn.maquette_backend.dto.UEDTO;

import java.util.List;

public interface UEService {
    UEDTO ajouterUE(UEDTO ueDTO);
    UEDTO modifierUE(Long id, UEDTO ueDTO);
    void supprimerUE(Long id);
    UEDTO rechercherUE(Long id);
    List<UEDTO> listerUEs();
}