package com.transtu.pacbus.controller;

import com.transtu.pacbus.entity.ParcReseauFerre;
import com.transtu.pacbus.service.ParcReseauFerreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/parc-reseau-ferre")
public class ParcReseauFerreController {

    @Autowired
    private ParcReseauFerreService service;

    // ✅ Créer
    @PostMapping
    @Operation(summary = "Create a new vehicle")
    public ResponseEntity<ParcReseauFerre> create(@RequestBody ParcReseauFerre parc) {
        return ResponseEntity.ok(service.save(parc));
    }

    // ✅ Lire tout
    @GetMapping
    @Operation(summary = "Get all vehicles")
    public ResponseEntity<List<ParcReseauFerre>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ Lire par ID
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID")
    public ResponseEntity<ParcReseauFerre> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Supprimer
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vehicle by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Mettre à jour
    @PutMapping("/{id}")
    @Operation(summary = "Update vehicle by ID")
    public ResponseEntity<ParcReseauFerre> update(@PathVariable Long id,
                                                  @RequestBody ParcReseauFerre parcDetails) {
        return service.getById(id)
                .map(parc -> {
                    parc.setMatricule(parcDetails.getMatricule());
                    parc.setNumber(parcDetails.getNumber());
                    parc.setType(parcDetails.getType());
                    parc.setTransportType(parcDetails.getTransportType());
                    parc.setDepcod(parcDetails.getDepcod());
                    parc.setStatus(parcDetails.getStatus());
                    return ResponseEntity.ok(service.save(parc));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Recherche disponible + pagination
    @GetMapping("/searchs")
    @Operation(summary = "Search for available vehicles with pagination",
            description = "Returns a paginated list of vehicles where status is ACTIVE and matches the keyword in matricule, number, or type")
    public Page<ParcReseauFerre> search(@RequestParam String keyword,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) {
        return service.searchDisponible(keyword, PageRequest.of(page, size));
    }
    
    
 // ✅ Recherche disponible + filtrage par transportType
    @GetMapping("/search")
    @Operation(summary = "Search for available vehicles with pagination",
               description = "Filters by status ACTIVE and optionally by transportType (2=METRO, 3=TGM), and keyword in matricule or number")
    public Page<ParcReseauFerre> search(
            @RequestParam String keyword,
            @RequestParam(required = false) Integer transportTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return service.searchDisponible(keyword, transportTypeId, PageRequest.of(page, size));
    }
    
}
