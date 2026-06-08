package com.transtu.pacbus.repository;



import com.transtu.pacbus.entity.ParcReseauFerre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParcReseauFerreRepository extends JpaRepository<ParcReseauFerre, Long> {

    // Recherche disponible = status = ACTIVE + keyword dans matricule, number ou type
    @Query("SELECT p FROM ParcReseauFerre p " +
           "WHERE p.status = com.transtu.pacbus.entity.MaterialStatus.ACTIVE " +
           "AND (UPPER(p.matricule) LIKE %:keyword% OR UPPER(p.number) LIKE %:keyword% OR UPPER(p.type) LIKE %:keyword%)")
    Page<ParcReseauFerre> searchDisponible(@Param("keyword") String keyword, Pageable pageable);
    
    
    // Recherche disponible filtrée par type (METRO ou TGM)
    @Query("SELECT p FROM ParcReseauFerre p " +
           "WHERE p.status = com.transtu.pacbus.entity.MaterialStatus.ACTIVE " +
           "AND p.type = :type " +
           "AND (UPPER(p.matricule) LIKE %:keyword% OR UPPER(p.number) LIKE %:keyword%)")
    Page<ParcReseauFerre> searchByType(@Param("keyword") String keyword,
                                       @Param("type") String type,
                                       Pageable pageable);
}
