package uasz.sn.maquette_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.ClasseDTO;
import uasz.sn.maquette_backend.mapper.ClasseMapper;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.repository.ClasseRepository;
import uasz.sn.maquette_backend.repository.FormationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public ClasseDTO ajouterClasse(ClasseDTO classeDTO) {
        Classe classe = ClasseMapper.INSTANCE.toEntity(classeDTO);

        // Associer la formation à la classe
        Formation formation = formationRepository.findById(classeDTO.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        classe.setFormation(formation);

        classe = classeRepository.save(classe);
        return ClasseMapper.INSTANCE.toDto(classe);
    }

    @Override
    public ClasseDTO modifierClasse(Long id, ClasseDTO classeDTO) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        // Mettre à jour l'entité existante
        ClasseMapper.INSTANCE.updateEntity(classeDTO, classe);

        // Mettre à jour la formation si nécessaire
        if (classeDTO.getFormationId() != null) {
            Formation formation = formationRepository.findById(classeDTO.getFormationId())
                    .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
            classe.setFormation(formation);
        }

        classe = classeRepository.save(classe);
        return ClasseMapper.INSTANCE.toDto(classe);
    }


    @Override
    public void supprimerClasse(Long id) {
        classeRepository.deleteById(id);
    }

    @Override
    public ClasseDTO rechercherClasse(Long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        return ClasseMapper.INSTANCE.toDto(classe);
    }

    @Override
    public List<ClasseDTO> listerClasses() {
        return classeRepository.findAll().stream()
                .map(ClasseMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void activerOuDesactiverClasse(Long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        classe.setActif(!classe.isActif());
        classeRepository.save(classe);
    }

    @Override
    public void archiverOuDesarchiverClasse(Long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        classe.setArchive(!classe.isArchive());
        classeRepository.save(classe);
    }
}
