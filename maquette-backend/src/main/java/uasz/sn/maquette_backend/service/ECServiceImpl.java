package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.ECDTO;
import uasz.sn.maquette_backend.mapper.ECMapper;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.UE;
import uasz.sn.maquette_backend.repository.ECRepository;
import uasz.sn.maquette_backend.repository.UERepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ECServiceImpl implements ECService {

    @Autowired
    private ECRepository ecRepository;

    @Autowired
    private UERepository ueRepository;

    @Override
    public ECDTO ajouterEC(ECDTO ecDTO) {
        EC ec = ECMapper.INSTANCE.toEntity(ecDTO);

        // Associer l'EC à l'UE
        UE ue = ueRepository.findById(ecDTO.getUeId())
                .orElseThrow(() -> new RuntimeException("UE non trouvée"));
        ec.setUe(ue);

        ec = ecRepository.save(ec);
        return ECMapper.INSTANCE.toDto(ec);
    }

    @Override
    public ECDTO modifierEC(Long id, ECDTO ecDTO) {
        EC ec = ecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EC non trouvé"));

        ECMapper.INSTANCE.updateEntity(ecDTO, ec); // Utilisation de la nouvelle méthode

        // Mettre à jour l'UE si nécessaire
        if (ecDTO.getUeId() != null) {
            UE ue = ueRepository.findById(ecDTO.getUeId())
                    .orElseThrow(() -> new RuntimeException("UE non trouvée"));
            ec.setUe(ue);
        }

        ec = ecRepository.save(ec);
        return ECMapper.INSTANCE.toDto(ec);
    }

    @Override
    public void supprimerEC(Long id) {
        ecRepository.deleteById(id);
    }

    @Override
    public ECDTO rechercherEC(Long id) {
        EC ec = ecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EC non trouvé"));
        return ECMapper.INSTANCE.toDto(ec);
    }

    @Override
    public List<ECDTO> listerECs() {
        return ecRepository.findAll().stream()
                .map(ECMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ECDTO> listerECsParUE(Long ueId) {
        return ecRepository.findByUeId(ueId).stream()
                .map(ECMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}