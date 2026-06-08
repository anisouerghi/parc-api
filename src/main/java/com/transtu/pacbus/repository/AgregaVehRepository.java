package com.transtu.pacbus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.transtu.pacbus.entity.AgregaVeh;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgregaVehRepository extends JpaRepository<AgregaVeh, String> {

    @Query("SELECT a FROM AgregaVeh a WHERE UPPER(a.disp) = 'DISPONIBLE' AND " +
           "(UPPER(a.vehnum) LIKE %:keyword% OR UPPER(a.vehimmat) LIKE %:keyword% OR UPPER(a.designe) LIKE %:keyword%)")
    Page<AgregaVeh> searchDisponible(@Param("keyword") String keyword, Pageable pageable);
}
