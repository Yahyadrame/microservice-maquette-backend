package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uasz.sn.maquette_backend.modele.Formation;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {

}