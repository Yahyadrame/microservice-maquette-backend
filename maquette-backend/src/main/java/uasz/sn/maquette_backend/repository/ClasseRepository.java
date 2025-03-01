package uasz.sn.maquette_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.maquette_backend.modele.Classe;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    // Find all classes associated with a specific formation ID
    List<Classe> findByFormationId(Long formationId);
}