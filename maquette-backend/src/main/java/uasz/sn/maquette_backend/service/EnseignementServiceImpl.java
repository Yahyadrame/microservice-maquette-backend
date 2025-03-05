package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.mapper.EnseignementMapper;
import uasz.sn.maquette_backend.modele.Enseignement;
import uasz.sn.maquette_backend.repository.EnseignementRepository;

@Service
public class EnseignementServiceImpl implements EnseignementService {

    @Autowired
    private EnseignementRepository enseignementRepository;

    @Override
    public EnseignementDTO creerEnseignement(EnseignementDTO enseignementDTO) {
        Enseignement enseignement = EnseignementMapper.INSTANCE.toEntity(enseignementDTO);
        enseignement = enseignementRepository.save(enseignement);
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }
}