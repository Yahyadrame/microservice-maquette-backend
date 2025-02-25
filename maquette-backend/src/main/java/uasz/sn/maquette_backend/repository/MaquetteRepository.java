package uasz.sn.maquette_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.maquette_backend.modele.Maquette;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaquetteRepository extends JpaRepository<Maquette, Long> {

    // Trouver une maquette par son nom
    Optional<Maquette> findByNom(String nom);

    // Trouver toutes les maquettes associées à une classe
    List<Maquette> findByClasseId(Long classeId);

    // Trouver toutes les maquettes associées à une formation
    List<Maquette> findByFormationId(Long formationId);

    // Trouver toutes les maquettes associées à une UE
    List<Maquette> findByUes_Id(Long ueId);

    // Trouver une maquette par son ID
    Optional<Maquette> findById(Long id);

    // Trouver toutes les maquettes actives
    List<Maquette> findByActifTrue();

    // Trouver toutes les maquettes archivées
    List<Maquette> findByArchiveTrue();
}