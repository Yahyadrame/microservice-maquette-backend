package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.ECDTO;
import uasz.sn.maquette_backend.service.ECService;

import java.util.List;

@RestController
@RequestMapping("/api/ecs")
@CrossOrigin(origins = "http://localhost:3001") // Si le frontend tourne sur un autre port
public class ECController {

    @Autowired
    private ECService ecService;

    @PostMapping
    public ResponseEntity<ECDTO> ajouterEC(@RequestBody ECDTO ecDTO) {
        ECDTO savedEC = ecService.ajouterEC(ecDTO);
        return ResponseEntity.ok(savedEC);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ECDTO> modifierEC(@PathVariable Long id, @RequestBody ECDTO ecDTO) {
        ECDTO updatedEC = ecService.modifierEC(id, ecDTO);
        return ResponseEntity.ok(updatedEC);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEC(@PathVariable Long id) {
        ecService.supprimerEC(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ECDTO> rechercherEC(@PathVariable Long id) {
        ECDTO ec = ecService.rechercherEC(id);
        return ResponseEntity.ok(ec);
    }

    @GetMapping
    public ResponseEntity<List<ECDTO>> listerECs() {
        List<ECDTO> ecs = ecService.listerECs();
        return ResponseEntity.ok(ecs);
    }

    @GetMapping("/ue/{ueId}")
    public ResponseEntity<List<ECDTO>> listerECsParUE(@PathVariable Long ueId) {
        List<ECDTO> ecs = ecService.listerECsParUE(ueId);
        return ResponseEntity.ok(ecs);
    }
}