package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.maquette_backend.modele.Enseignement;

import java.util.List;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {

    // Trouver tous les enseignements par utilisateur
    List<Enseignement> findByUtilisateurId(Long utilisateurId);

    // Trouver tous les enseignements par EC
    List<Enseignement> findByEcId(Long ecId);

    // Trouver tous les enseignements par classe
    List<Enseignement> findByClasseId(Long classeId);

    // Trouver tous les enseignements validés
    List<Enseignement> findByValidationChefTrue();

    // Trouver tous les enseignements non validés
    List<Enseignement> findByValidationChefFalse();
}