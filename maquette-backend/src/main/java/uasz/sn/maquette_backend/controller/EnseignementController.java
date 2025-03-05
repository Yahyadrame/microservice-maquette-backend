package uasz.sn.maquette_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.service.EnseignementService;

@RestController
@RequestMapping("/api/enseignements")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class EnseignementController {

    @Autowired
    private EnseignementService enseignementService;

    @PostMapping
    public ResponseEntity<EnseignementDTO> creerEnseignement(@RequestBody EnseignementDTO enseignementDTO) {
        EnseignementDTO savedEnseignement = enseignementService.creerEnseignement(enseignementDTO);
        return ResponseEntity.ok(savedEnseignement);
    }
}