package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.mapper.EnseignementMapper;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.Enseignement;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.repository.ClasseRepository;
import uasz.sn.maquette_backend.repository.EnseignementRepository;
import uasz.sn.maquette_backend.repository.FormationRepository;

@Service
public class EnseignementServiceImpl implements EnseignementService {

    @Autowired
    private EnseignementRepository enseignementRepository;

    @Autowired
    private ClasseRepository classeRepository; // Ajout du repository pour Classe

    @Autowired
    private FormationRepository formationRepository; // Ajout du repository pour Formation

    @Override
    public EnseignementDTO creerEnseignement(EnseignementDTO enseignementDTO) {
        // Vérifier si les IDs sont présents
        if (enseignementDTO.getClasseId() == null) {
            throw new IllegalArgumentException("Une classe doit être spécifiée pour l'enseignement");
        }

        if (enseignementDTO.getFormationId() == null) {
            throw new IllegalArgumentException("Une formation doit être spécifiée pour l'enseignement");
        }

        // Récupération des entités en base
        Classe classe = classeRepository.findById(enseignementDTO.getClasseId())
                .orElseThrow(() -> new IllegalArgumentException("Classe non trouvée avec l'ID : " + enseignementDTO.getClasseId()));

        Formation formation = formationRepository.findById(enseignementDTO.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée avec l'ID : " + enseignementDTO.getFormationId()));

        // Conversion du DTO en entité
        Enseignement enseignement = EnseignementMapper.INSTANCE.toEntity(enseignementDTO);

        // Affectation des entités récupérées
        enseignement.setClasse(classe);
        enseignement.setFormation(formation);

        // Sauvegarde dans la base
        enseignement = enseignementRepository.save(enseignement);

        // Retourner le DTO
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }
}
