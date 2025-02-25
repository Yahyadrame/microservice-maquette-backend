package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.maquette_backend.modele.EC;

import java.util.List;

public interface ECRepository extends JpaRepository<EC, Long> {
    List<EC> findByUeId(Long ueId); // Trouver tous les ECs associés à une UE
}