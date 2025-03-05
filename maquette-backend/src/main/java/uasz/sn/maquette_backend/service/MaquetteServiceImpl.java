package uasz.sn.maquette_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.dto.MaquetteDTO;
import uasz.sn.maquette_backend.mapper.MaquetteMapper;
import uasz.sn.maquette_backend.modele.*;
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

    @Autowired
    private EnseignementService enseignementService;


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

        // Créer les enseignements pour chaque UE et EC
        for (UE ue : ues) {
            for (EC ec : ue.getEcs()) {
                // Vérifier les heures de CM, TD et TP pour créer les enseignements correspondants
                if (ec.getCm() > 0) {
                    EnseignementDTO enseignementCM = new EnseignementDTO();
                    enseignementCM.setEcId(ec.getId());
                    enseignementCM.setType("CM");
                    enseignementCM.setClasseId(classe.getId());
                    enseignementCM.setSemestres(maquetteDTO.getSemestres());
                    enseignementCM.setFormationId(formation.getId());
                    enseignementService.creerEnseignement(enseignementCM);
                }

                if (ec.getTd() > 0) {
                    EnseignementDTO enseignementTD = new EnseignementDTO();
                    enseignementTD.setEcId(ec.getId());
                    enseignementTD.setType("TD");
                    enseignementTD.setClasseId(classe.getId());
                    enseignementTD.setSemestres(maquetteDTO.getSemestres());
                    enseignementTD.setFormationId(formation.getId());
                    enseignementService.creerEnseignement(enseignementTD);
                }

                if (ec.getTp() > 0) {
                    EnseignementDTO enseignementTP = new EnseignementDTO();
                    enseignementTP.setEcId(ec.getId());
                    enseignementTP.setType("TP");
                    enseignementTP.setClasseId(classe.getId());
                    enseignementTP.setSemestres(maquetteDTO.getSemestres());
                    enseignementTP.setFormationId(formation.getId());
                    enseignementService.creerEnseignement(enseignementTP);
                }
            }
        }

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