package com.transtu.pacbus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtu.pacbus.dto.AgregaVehFilter;
import com.transtu.pacbus.entity.AgregaVeh;
import com.transtu.pacbus.repository.AgregaVehFilterRepository;
import com.transtu.pacbus.specification.AgregaVehSpecification;

/**
 * Service dédié au filtrage multicritère et paginé de {@link AgregaVeh}.
 *
 * <p>Classe purement additive : {@code AgregaVehService} reste inchangé. Les
 * critères du filtre sont combinés en AND ; un filtre vide retourne tous les
 * enregistrements, paginés et triés selon le {@link Pageable} fourni.</p>
 */
@Service
@Transactional(readOnly = true)
public class AgregaVehFilterService {

    private final AgregaVehFilterRepository repository;

    public AgregaVehFilterService(AgregaVehFilterRepository repository) {
        this.repository = repository;
    }

    public Page<AgregaVeh> filter(AgregaVehFilter filter, Pageable pageable) {
        return repository.findAll(AgregaVehSpecification.build(filter), pageable);
    }
}
