package uasz.sn.maquette_backend.service;


import uasz.sn.maquette_backend.dto.ClasseDTO;

import java.util.List;

public interface ClasseService {
    ClasseDTO ajouterClasse(ClasseDTO classeDTO);
    ClasseDTO modifierClasse(Long id, ClasseDTO classeDTO);
    void supprimerClasse(Long id);
    ClasseDTO rechercherClasse(Long id);
    List<ClasseDTO> listerClasses();
    void activerOuDesactiverClasse(Long id);
    void archiverOuDesarchiverClasse(Long id);
    //pour renvoyer les classe d'une formation
    List<ClasseDTO> listerClassesParFormation(Long formationId);
}