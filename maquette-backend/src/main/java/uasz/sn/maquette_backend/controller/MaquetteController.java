package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.MaquetteDTO;
import uasz.sn.maquette_backend.service.MaquetteService;

import java.util.List;

@RestController
@RequestMapping("/api/maquettes")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
 // Si le frontend tourne sur un autre port
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;

    @PostMapping
    public ResponseEntity<MaquetteDTO> ajouterMaquette(@RequestBody MaquetteDTO maquetteDTO) {
        MaquetteDTO savedMaquette = maquetteService.ajouterMaquette(maquetteDTO);
        return ResponseEntity.ok(savedMaquette);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaquetteDTO> modifierMaquette(@PathVariable Long id, @RequestBody MaquetteDTO maquetteDTO) {
        MaquetteDTO updatedMaquette = maquetteService.modifierMaquette(id, maquetteDTO);
        return ResponseEntity.ok(updatedMaquette);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMaquette(@PathVariable Long id) {
        maquetteService.supprimerMaquette(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquetteDTO> rechercherMaquette(@PathVariable Long id) {
        MaquetteDTO maquette = maquetteService.rechercherMaquette(id);
        return ResponseEntity.ok(maquette);
    }

    @GetMapping
    public ResponseEntity<List<MaquetteDTO>> listerMaquettes() {
        List<MaquetteDTO> maquettes = maquetteService.listerMaquettes();
        return ResponseEntity.ok(maquettes);
    }

    @PostMapping("/{id}/activer-desactiver")
    public ResponseEntity<Void> activerOuDesactiverMaquette(@PathVariable Long id) {
        maquetteService.activerOuDesactiverMaquette(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/archiver-desarchiver")
    public ResponseEntity<Void> archiverOuDesarchiverMaquette(@PathVariable Long id) {
        maquetteService.archiverOuDesarchiverMaquette(id);
        return ResponseEntity.noContent().build();
    }
}