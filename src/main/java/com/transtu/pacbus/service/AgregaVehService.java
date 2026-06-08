package com.transtu.pacbus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.transtu.pacbus.entity.AgregaVeh;
import com.transtu.pacbus.repository.AgregaVehRepository;

@Service
@Transactional(readOnly = true)
public class AgregaVehService {

    private final AgregaVehRepository repository;

    public AgregaVehService(AgregaVehRepository repository) {
        this.repository = repository;
    }

    public Page<AgregaVeh> searchDisponible(String keyword, Pageable pageable) {
        return repository.searchDisponible(keyword.toUpperCase(), pageable);
    }
}
