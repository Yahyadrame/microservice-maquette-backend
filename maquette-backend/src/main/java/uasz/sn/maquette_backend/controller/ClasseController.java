package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.ClasseDTO;
import uasz.sn.maquette_backend.service.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:3001") // Si le frontend tourne sur un autre port
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @PostMapping
    public ResponseEntity<ClasseDTO> ajouterClasse(@RequestBody ClasseDTO classeDTO) {
        ClasseDTO savedClasse = classeService.ajouterClasse(classeDTO);
        return ResponseEntity.ok(savedClasse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDTO> modifierClasse(@PathVariable Long id, @RequestBody ClasseDTO classeDTO) {
        ClasseDTO updatedClasse = classeService.modifierClasse(id, classeDTO);
        return ResponseEntity.ok(updatedClasse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClasse(@PathVariable Long id) {
        classeService.supprimerClasse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> rechercherClasse(@PathVariable Long id) {
        ClasseDTO classe = classeService.rechercherClasse(id);
        return ResponseEntity.ok(classe);
    }

    @GetMapping
    public ResponseEntity<List<ClasseDTO>> listerClasses() {
        List<ClasseDTO> classes = classeService.listerClasses();
        return ResponseEntity.ok(classes);
    }
   @GetMapping("/formation/{formationId}")
   public ResponseEntity<List<ClasseDTO>> listerClassesParFormation(@PathVariable Long formationId) {
       List<ClasseDTO> classes = classeService.listerClassesParFormation(formationId);
       return ResponseEntity.ok(classes);
   }


    @PostMapping("/{id}/activer-desactiver")
    public ResponseEntity<Void> activerOuDesactiverClasse(@PathVariable Long id) {
        classeService.activerOuDesactiverClasse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/archiver-desarchiver")
    public ResponseEntity<Void> archiverOuDesarchiverClasse(@PathVariable Long id) {
        classeService.archiverOuDesarchiverClasse(id);
        return ResponseEntity.noContent().build();
    }
}
