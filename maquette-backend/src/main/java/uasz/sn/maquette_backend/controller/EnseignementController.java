package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.service.EnseignementService;

import java.util.List;

@RestController
@RequestMapping("/api/enseignements")
@CrossOrigin(origins = "http://localhost:3001")
public class EnseignementController {

    @Autowired
    private EnseignementService enseignementService;

    // Créer un enseignement
    @PostMapping
    public ResponseEntity<EnseignementDTO> creerEnseignement(@RequestBody EnseignementDTO enseignementDTO) {
        EnseignementDTO savedEnseignement = enseignementService.creerEnseignement(enseignementDTO);
        return ResponseEntity.ok(savedEnseignement);
    }

    // Récupérer tous les enseignements
    @GetMapping
    public ResponseEntity<List<EnseignementDTO>> getAllEnseignements() {
        List<EnseignementDTO> enseignements = enseignementService.getAllEnseignements();
        return ResponseEntity.ok(enseignements);
    }
}