package com.transtu.pacbus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.transtu.pacbus.entity.AgregaVeh;

/**
 * Repository dédié au filtrage multicritère de {@link AgregaVeh} via les
 * {@code Specification} de Spring Data JPA.
 *
 * <p>Classe purement additive : elle coexiste avec {@code AgregaVehRepository}
 * (laissé intact) et n'impacte aucune requête existante. Spring Data autorise
 * plusieurs repositories pour une même entité.</p>
 */
public interface AgregaVehFilterRepository
        extends JpaRepository<AgregaVeh, String>, JpaSpecificationExecutor<AgregaVeh> {
}
