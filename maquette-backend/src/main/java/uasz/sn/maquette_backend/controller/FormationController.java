package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.FormationDTO;
import uasz.sn.maquette_backend.service.FormationService;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @PostMapping
    public ResponseEntity<FormationDTO> ajouterFormation(@RequestBody FormationDTO formationDTO) {
        FormationDTO savedFormation = formationService.ajouterFormation(formationDTO);
        return ResponseEntity.ok(savedFormation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> modifierFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        FormationDTO updatedFormation = formationService.modifierFormation(id, formationDTO);
        return ResponseEntity.ok(updatedFormation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerFormation(@PathVariable Long id) {
        formationService.supprimerFormation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationDTO> rechercherFormation(@PathVariable Long id) {
        FormationDTO formation = formationService.rechercherFormation(id);
        return ResponseEntity.ok(formation);
    }

    @GetMapping
    public ResponseEntity<List<FormationDTO>> listerFormations() {
        List<FormationDTO> formations = formationService.listerFormations();
        return ResponseEntity.ok(formations);
    }

    @PostMapping("/{id}/activer-desactiver")
    public ResponseEntity<Void> activerOuDesactiverFormation(@PathVariable Long id) {
        formationService.activerOuDesactiverFormation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/archiver-desarchiver")
    public ResponseEntity<Void> archiverOuDesarchiverFormation(@PathVariable Long id) {
        formationService.archiverOuDesarchiverFormation(id);
        return ResponseEntity.noContent().build();
    }
}