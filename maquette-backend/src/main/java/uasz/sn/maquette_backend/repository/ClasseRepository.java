package uasz.sn.maquette_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.maquette_backend.modele.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
}