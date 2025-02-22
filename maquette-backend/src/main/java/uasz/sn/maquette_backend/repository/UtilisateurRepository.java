package uasz.sn.maquette_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.maquette_backend.modele.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}