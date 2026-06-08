package com.transtu.pacbus.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtu.pacbus.dto.AgregaVehFilter;
import com.transtu.pacbus.entity.AgregaVeh;
import com.transtu.pacbus.service.AgregaVehFilterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Endpoint dédié au filtrage multicritère et paginé des véhicules.
 *
 * <p>Contrôleur purement additif : il n'impacte pas le contrôleur existant
 * {@code AgregaVehController} ({@code /api/materials/search}).</p>
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vehicules")
@Tag(name = "Vehicle Search", description = "Filtrage multicritère et paginé des véhicules (AgregaVeh)")
public class VehiculeSearchController {

    private final AgregaVehFilterService service;

    public VehiculeSearchController(AgregaVehFilterService service) {
        this.service = service;
    }

    @Operation(
            summary = "Recherche multicritère paginée des véhicules",
            description = "Filtre dynamiquement les véhicules selon les critères fournis (combinés en AND) : "
                    + "listes IN (listDepcod, listVehnum, listVehimmat), égalités optionnelles "
                    + "(etacod, etavehcod) et intervalles de dates (affectdatdb, vehdatcartgris). "
                    + "Tous les critères sont optionnels. Supporte la pagination et le tri "
                    + "(page, size, sort).")
    @GetMapping("/search")
    public Page<AgregaVeh> search(
            @ModelAttribute AgregaVehFilter filter,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return service.filter(filter, pageable);
    }
}
