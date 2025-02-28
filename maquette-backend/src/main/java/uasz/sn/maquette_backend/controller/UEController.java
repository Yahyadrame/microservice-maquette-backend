package uasz.sn.maquette_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.UEDTO;
import uasz.sn.maquette_backend.service.UEService;

import java.util.List;
@RestController
@RequestMapping("/api/ues")
@CrossOrigin(origins = "http://localhost:3000") // Si le frontend tourne sur un autre port
public class UEController {

    @Autowired
    private UEService ueService;

    @PostMapping
    public ResponseEntity<UEDTO> ajouterUE(@RequestBody UEDTO ueDTO) {
        UEDTO savedUE = ueService.ajouterUE(ueDTO);
        return ResponseEntity.ok(savedUE);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UEDTO> modifierUE(@PathVariable Long id, @RequestBody UEDTO ueDTO) {
        UEDTO updatedUE = ueService.modifierUE(id, ueDTO);
        return ResponseEntity.ok(updatedUE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUE(@PathVariable Long id) {
        ueService.supprimerUE(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UEDTO> rechercherUE(@PathVariable Long id) {
        UEDTO ue = ueService.rechercherUE(id);
        return ResponseEntity.ok(ue);
    }

    @GetMapping
    public ResponseEntity<List<UEDTO>> listerUEs() {
        List<UEDTO> ues = ueService.listerUEs();
        return ResponseEntity.ok(ues);
    }
}