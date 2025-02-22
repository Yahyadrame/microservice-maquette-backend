package uasz.sn.maquette_backend.service;


import uasz.sn.maquette_backend.modele.Utilisateur;

public interface UtilisateurService {
    Utilisateur getUtilisateurById(Long id);
}