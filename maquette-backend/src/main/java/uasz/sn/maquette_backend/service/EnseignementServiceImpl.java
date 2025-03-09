package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.mapper.EnseignementMapper;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.Enseignement;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.repository.ClasseRepository;
import uasz.sn.maquette_backend.repository.ECRepository;
import uasz.sn.maquette_backend.repository.EnseignementRepository;
import uasz.sn.maquette_backend.repository.FormationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnseignementServiceImpl implements EnseignementService {

    @Autowired
    private EnseignementRepository enseignementRepository;

    @Autowired
    private ClasseRepository classeRepository; // Ajout du repository pour Classe

    @Autowired
    private FormationRepository formationRepository; // Ajout du repository pour Formation

    @Autowired
    private ECRepository ecRepository; // Ajout du repository pour Formation

    @Autowired
    private EnseignementMapper enseignementMapper;



    @Override
    public EnseignementDTO creerEnseignement(EnseignementDTO enseignementDTO) {
        // Vérifier si les IDs sont présents
        if (enseignementDTO.getClasseId() == null) {
            throw new IllegalArgumentException("Une classe doit être spécifiée pour l'enseignement");
        }

        if (enseignementDTO.getFormationId() == null) {
            throw new IllegalArgumentException("Une formation doit être spécifiée pour l'enseignement");
        }

        if (enseignementDTO.getEcId() == null) {
            throw new IllegalArgumentException("Une ec doit être spécifiée pour l'enseignement");
        }

        // Récupération des entités en base
        Classe classe = classeRepository.findById(enseignementDTO.getClasseId())
                .orElseThrow(() -> new IllegalArgumentException("Classe non trouvée avec l'ID : " + enseignementDTO.getClasseId()));

        Formation formation = formationRepository.findById(enseignementDTO.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée avec l'ID : " + enseignementDTO.getFormationId()));

        EC ec  = ecRepository.findById(enseignementDTO.getEcId())
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée avec l'ID : " + enseignementDTO.getEcId()));

        // Conversion du DTO en entité
        Enseignement enseignement = EnseignementMapper.INSTANCE.toEntity(enseignementDTO);

        // Affectation des entités récupérées
        enseignement.setClasse(classe);
        enseignement.setFormation(formation);
        enseignement.setEc(ec);


        // Sauvegarde dans la base
        enseignement = enseignementRepository.save(enseignement);

        // Retourner le DTO
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }

    @Override
    public List<EnseignementDTO> getAllEnseignements() {
        List<Enseignement> enseignements = enseignementRepository.findAll();
        return enseignements.stream()
                .map(enseignementMapper::toDto)
                .collect(Collectors.toList());
    }

     //afiiche en fonction de l'id de la classe
     @Override
     public List<EnseignementDTO> getEnseignementsByClasseId(Long classeId) {
         // Récupération de la classe par son ID
         Classe classe = classeRepository.findById(classeId)
                 .orElseThrow(() -> new IllegalArgumentException("Classe non trouvée avec l'ID : " + classeId));

         // Récupération des enseignements liés à cette classe
         List<Enseignement> enseignements = enseignementRepository.findByClasse(classe);

         // Retourner la liste des enseignements en tant que DTO
         return enseignements.stream()
                 .map(enseignement -> {
                     EnseignementDTO dto = enseignementMapper.toDto(enseignement);

                     // Récupérer la formation associée à l'enseignement et ajouter son nom
                     Formation formation = enseignement.getFormation(); // Assurez-vous que "getFormation" est implémenté
                     if (formation != null) {
                         dto.setFormationNom(formation.getNom()); // Ajouter le nom de la formation
                     }

                     // Récupérer l'EC associé à l'enseignement et ajouter son libellé
                     EC ec = enseignement.getEc();  // Assurez-vous que "getEc" est implémenté
                     if (ec != null) {
                         dto.setEcLibelle(ec.getLibelle());  // Ajouter le libellé de l'EC
                     }

                     // Récupérer la classe associée à l'enseignement et ajouter son nom
                     if (classe != null) {
                         dto.setClasseNom(classe.getNom());  // Ajouter le nom de la classe
                     }

                     return dto;
                 })
                 .collect(Collectors.toList());
     }


}

