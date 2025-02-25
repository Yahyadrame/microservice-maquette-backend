package uasz.sn.maquette_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.mapper.EnseignementMapper;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.Enseignement;
import uasz.sn.maquette_backend.modele.Utilisateur;
import uasz.sn.maquette_backend.repository.ClasseRepository;
import uasz.sn.maquette_backend.repository.ECRepository;
import uasz.sn.maquette_backend.repository.EnseignementRepository;
import uasz.sn.maquette_backend.repository.UtilisateurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnseignementServiceImpl implements EnseignementService {

    @Autowired
    private EnseignementRepository enseignementRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ECRepository ecRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Override
    public EnseignementDTO ajouterEnseignement(EnseignementDTO enseignementDTO) {
        Enseignement enseignement = EnseignementMapper.INSTANCE.toEntity(enseignementDTO);

        // Associer l'utilisateur, l'EC et la classe
        Utilisateur utilisateur = utilisateurRepository.findById(enseignementDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        EC ec = ecRepository.findById(enseignementDTO.getEcId())
                .orElseThrow(() -> new RuntimeException("EC non trouvé"));
        Classe classe = classeRepository.findById(enseignementDTO.getClasseId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        enseignement.setUtilisateur(utilisateur);
        enseignement.setEc(ec);
        enseignement.setClasse(classe);

        enseignement = enseignementRepository.save(enseignement);
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }

    @Override
    public EnseignementDTO modifierEnseignement(Long id, EnseignementDTO enseignementDTO) {
        Enseignement enseignement = enseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignement non trouvé"));

        // Mise à jour de l'objet existant
        EnseignementMapper.INSTANCE.updateEntity(enseignementDTO, enseignement);

        // Mise à jour des associations si elles sont spécifiées
        if (enseignementDTO.getUtilisateurId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(enseignementDTO.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            enseignement.setUtilisateur(utilisateur);
        }
        if (enseignementDTO.getEcId() != null) {
            EC ec = ecRepository.findById(enseignementDTO.getEcId())
                    .orElseThrow(() -> new RuntimeException("EC non trouvé"));
            enseignement.setEc(ec);
        }
        if (enseignementDTO.getClasseId() != null) {
            Classe classe = classeRepository.findById(enseignementDTO.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
            enseignement.setClasse(classe);
        }

        enseignement = enseignementRepository.save(enseignement);
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }


    @Override
    public void supprimerEnseignement(Long id) {
        enseignementRepository.deleteById(id);
    }

    @Override
    public EnseignementDTO rechercherEnseignement(Long id) {
        Enseignement enseignement = enseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignement non trouvé"));
        return EnseignementMapper.INSTANCE.toDto(enseignement);
    }

    @Override
    public List<EnseignementDTO> listerEnseignements() {
        return enseignementRepository.findAll().stream()
                .map(EnseignementMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void validerEnseignement(Long id) {
        Enseignement enseignement = enseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignement non trouvé"));
        enseignement.setValidationChef(true);
        enseignementRepository.save(enseignement);
    }
}