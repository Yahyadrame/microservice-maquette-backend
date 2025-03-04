package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.Enseignement;

import java.util.List;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {

}