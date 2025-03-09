package uasz.sn.maquette_backend.service;

import uasz.sn.maquette_backend.dto.EnseignementDTO;

import java.util.List;

public interface EnseignementService {
    EnseignementDTO creerEnseignement(EnseignementDTO enseignementDTO);
    List<EnseignementDTO> getAllEnseignements();
    List<EnseignementDTO> getEnseignementsByClasseId(Long classeId);
}