package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.maquette_backend.modele.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}