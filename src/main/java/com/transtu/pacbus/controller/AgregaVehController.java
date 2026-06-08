package com.transtu.pacbus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.transtu.pacbus.entity.AgregaVeh;
import com.transtu.pacbus.service.AgregaVehService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/materials")
@Tag(name = "Vehicle Management", description = "Endpoints for searching available vehicles")

public class AgregaVehController {

    private final AgregaVehService service;

    public AgregaVehController(AgregaVehService service) {
        this.service = service;
    }

    @Operation(summary = "Search for available vehicles with pagination", description = "Returns a paginated list of vehicles where 'disp' is 'DISPONIBLE' and matches the keyword in vehnum, vehimmat, or designe.")
    @GetMapping("/search")
    public Page<AgregaVeh> search(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {
        return service.searchDisponible(keyword, PageRequest.of(page, size));
    }
}
