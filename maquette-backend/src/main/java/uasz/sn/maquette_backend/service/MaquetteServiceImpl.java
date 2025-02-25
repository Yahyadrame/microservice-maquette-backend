package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.MaquetteDTO;
import uasz.sn.maquette_backend.mapper.MaquetteMapper;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.modele.Maquette;
import uasz.sn.maquette_backend.modele.UE;
import uasz.sn.maquette_backend.repository.ClasseRepository;
import uasz.sn.maquette_backend.repository.FormationRepository;
import uasz.sn.maquette_backend.repository.MaquetteRepository;
import uasz.sn.maquette_backend.repository.UERepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaquetteServiceImpl implements MaquetteService {

    @Autowired
    private MaquetteRepository maquetteRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private UERepository ueRepository;

    @Override
    public MaquetteDTO ajouterMaquette(MaquetteDTO maquetteDTO) {
        Maquette maquette = MaquetteMapper.INSTANCE.toEntity(maquetteDTO);

        // Associer la classe et la formation
        Classe classe = classeRepository.findById(maquetteDTO.getClasseId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        Formation formation = formationRepository.findById(maquetteDTO.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        maquette.setClasse(classe);
        maquette.setFormation(formation);

        // Associer les UEs
        List<UE> ues = ueRepository.findAllById(maquetteDTO.getUesIds());
        maquette.setUes(ues);

        maquette = maquetteRepository.save(maquette);
        return MaquetteMapper.INSTANCE.toDto(maquette);
    }

    @Override
    public MaquetteDTO modifierMaquette(Long id, MaquetteDTO maquetteDTO) {
        Maquette maquette = maquetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquette non trouvée"));

        // Mise à jour de l'objet existant
        MaquetteMapper.INSTANCE.updateEntity(maquetteDTO, maquette);

        // Mettre à jour la classe et la formation si nécessaire
        if (maquetteDTO.getClasseId() != null) {
            Classe classe = classeRepository.findById(maquetteDTO.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
            maquette.setClasse(classe);
        }
        if (maquetteDTO.getFormationId() != null) {
            Formation formation = formationRepository.findById(maquetteDTO.getFormationId())
                    .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
            maquette.setFormation(formation);
        }

        // Mettre à jour les UEs
        if (maquetteDTO.getUesIds() != null) {
            List<UE> ues = ueRepository.findAllById(maquetteDTO.getUesIds());
            maquette.setUes(ues);
        }

        maquette = maquetteRepository.save(maquette);
        return MaquetteMapper.INSTANCE.toDto(maquette);
    }


    @Override
    public void supprimerMaquette(Long id) {
        maquetteRepository.deleteById(id);
    }

    @Override
    public MaquetteDTO rechercherMaquette(Long id) {
        Maquette maquette = maquetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquette non trouvée"));
        return MaquetteMapper.INSTANCE.toDto(maquette);
    }

    @Override
    public List<MaquetteDTO> listerMaquettes() {
        return maquetteRepository.findAll().stream()
                .map(MaquetteMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void activerOuDesactiverMaquette(Long id) {
        Maquette maquette = maquetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquette non trouvée"));
        maquette.setActif(!maquette.isActif());
        maquetteRepository.save(maquette);
    }

    @Override
    public void archiverOuDesarchiverMaquette(Long id) {
        Maquette maquette = maquetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maquette non trouvée"));
        maquette.setArchive(!maquette.isArchive());
        maquetteRepository.save(maquette);
    }
}