package uasz.sn.maquette_backend.service;


import uasz.sn.maquette_backend.dto.ECDTO;

import java.util.List;

public interface ECService {
    ECDTO ajouterEC(ECDTO ecDTO);
    ECDTO modifierEC(Long id, ECDTO ecDTO);
    void supprimerEC(Long id);
    ECDTO rechercherEC(Long id);
    List<ECDTO> listerECs();
    List<ECDTO> listerECsParUE(Long ueId);
}