package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.FormationDTO;
import uasz.sn.maquette_backend.mapper.FormationMapper;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.repository.FormationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public FormationDTO ajouterFormation(FormationDTO formationDTO) {
        Formation formation = FormationMapper.INSTANCE.toEntity(formationDTO);
        formation = formationRepository.save(formation);
        return FormationMapper.INSTANCE.toDto(formation);
    }

    @Override
    public FormationDTO modifierFormation(Long id, FormationDTO formationDTO) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        // Mettre à jour l'entité existante
        FormationMapper.INSTANCE.updateEntity(formationDTO, formation);

        formation = formationRepository.save(formation);
        return FormationMapper.INSTANCE.toDto(formation);
    }


    @Override
    public void supprimerFormation(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public FormationDTO rechercherFormation(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        return FormationMapper.INSTANCE.toDto(formation);
    }

    @Override
    public List<FormationDTO> listerFormations() {
        return formationRepository.findAll().stream()
                .map(FormationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void activerOuDesactiverFormation(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        formation.setActif(!formation.isActif());
        formationRepository.save(formation);
    }

    @Override
    public void archiverOuDesarchiverFormation(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        formation.setArchive(!formation.isArchive());
        formationRepository.save(formation);
    }
}