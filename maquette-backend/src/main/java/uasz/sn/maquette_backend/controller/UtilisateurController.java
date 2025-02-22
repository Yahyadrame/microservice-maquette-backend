package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.modele.Utilisateur;
import uasz.sn.maquette_backend.service.UtilisateurServiceImpl;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @GetMapping("/{id}")
    public Utilisateur getUtilisateur(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }
}