package com.transtu.pacbus.service;

import com.transtu.pacbus.entity.ParcReseauFerre;
import com.transtu.pacbus.repository.ParcReseauFerreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcReseauFerreService {

    @Autowired
    private ParcReseauFerreRepository repository;

    // CRUD simple
    public ParcReseauFerre save(ParcReseauFerre parc) {
        return repository.save(parc);
    }

    public Optional<ParcReseauFerre> getById(Long id) {
        return repository.findById(id);
    }

    public List<ParcReseauFerre> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ Recherche “disponible” avec JPQL
    public Page<ParcReseauFerre> searchDisponible(String keyword, Pageable pageable) {
        return repository.searchDisponible(keyword.toUpperCase(), pageable);
    }
    
    
    public Page<ParcReseauFerre> searchDisponible(String keyword, Integer transportTypeId, Pageable pageable) {
        String kw = keyword.toUpperCase();
        if (transportTypeId != null) {
            switch (transportTypeId) {
                case 2: // METRO
                    return repository.searchByType(kw, "METRO", pageable);
                case 3: // TGM
                    return repository.searchByType(kw, "TGM", pageable);
            }
        }
        // Sinon recherche standard sans filtrage de type
        return repository.searchDisponible(kw, pageable);
    }
    
    
    
}
