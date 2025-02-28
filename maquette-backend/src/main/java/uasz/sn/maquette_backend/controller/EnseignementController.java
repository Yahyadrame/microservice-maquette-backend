package uasz.sn.maquette_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.service.EnseignementService;

import java.util.List;

@RestController
@RequestMapping("/api/enseignements")
@CrossOrigin(origins = "http://localhost:3000") // Si le frontend tourne sur un autre port
public class EnseignementController {

    @Autowired
    private EnseignementService enseignementService;

    @PostMapping
    public ResponseEntity<EnseignementDTO> ajouterEnseignement(@RequestBody EnseignementDTO enseignementDTO) {
        EnseignementDTO savedEnseignement = enseignementService.ajouterEnseignement(enseignementDTO);
        return ResponseEntity.ok(savedEnseignement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnseignementDTO> modifierEnseignement(@PathVariable Long id, @RequestBody EnseignementDTO enseignementDTO) {
        EnseignementDTO updatedEnseignement = enseignementService.modifierEnseignement(id, enseignementDTO);
        return ResponseEntity.ok(updatedEnseignement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEnseignement(@PathVariable Long id) {
        enseignementService.supprimerEnseignement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnseignementDTO> rechercherEnseignement(@PathVariable Long id) {
        EnseignementDTO enseignement = enseignementService.rechercherEnseignement(id);
        return ResponseEntity.ok(enseignement);
    }

    @GetMapping
    public ResponseEntity<List<EnseignementDTO>> listerEnseignements() {
        List<EnseignementDTO> enseignements = enseignementService.listerEnseignements();
        return ResponseEntity.ok(enseignements);
    }

    @PostMapping("/{id}/valider")
    public ResponseEntity<Void> validerEnseignement(@PathVariable Long id) {
        enseignementService.validerEnseignement(id);
        return ResponseEntity.noContent().build();
    }
}