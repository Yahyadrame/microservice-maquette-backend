package uasz.sn.maquette_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.UEDTO;
import uasz.sn.maquette_backend.mapper.UEMapper;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.modele.UE;
import uasz.sn.maquette_backend.repository.UERepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UEServiceImpl implements UEService {

    @Autowired
    private UERepository ueRepository;

    @Override
    public UEDTO ajouterUE(UEDTO ueDTO) {
        UE ue = UEMapper.INSTANCE.toEntity(ueDTO);
        ue = ueRepository.save(ue);
        return UEMapper.INSTANCE.toDto(ue);
    }

    @Override
    public UEDTO modifierUE(Long id, UEDTO ueDTO) {
        UE ue = ueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UE non trouvée"));
        UEMapper.INSTANCE.updateEntity(ueDTO, ue);
        ue = ueRepository.save(ue);
        return UEMapper.INSTANCE.toDto(ue);
    }


    @Override
    public void supprimerUE(Long id) {
        ueRepository.deleteById(id);
    }

    @Override
    public UEDTO rechercherUE(Long id) {
        UE ue = ueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UE non trouvée"));
        return UEMapper.INSTANCE.toDto(ue);
    }

    @Override
    public List<UEDTO> listerUEs() {
        return ueRepository.findAll().stream()
                .map(UEMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    //archiver activer
    @Override
    public void activerOuDesactiverUe(Long id) {
        UE ue = ueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UE non trouvée"));
        ue.setActif(!ue.isActif());
        ueRepository.save(ue);
    }

    @Override
    public void archiverOuDesarchiverUe(Long id) {
        UE ue = ueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UE non trouvée"));
        ue.setArchive(!ue.isArchive());
        ueRepository.save(ue);
    }
}