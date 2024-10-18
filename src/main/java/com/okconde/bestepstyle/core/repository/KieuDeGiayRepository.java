package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.KieuDeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Created at 23/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
public interface KieuDeGiayRepository extends JpaRepository<KieuDeGiay, Long> {
    @Query("""
            select distinct kdg from KieuDeGiay kdg
            where (:maKieuDeGiay is null or kdg.maKieuDeGiay like %:maKieuDeGiay%)
            and (:tenKieuDeGiay is null or kdg.tenKieuDeGiay like %:tenKieuDeGiay%)
            """)
    Page<KieuDeGiay> searchPageKieuDeGiay(Pageable pageable,
                                          @Param("maKieuDeGiay") String maKieuDeGiay,
                                          @Param("tenKieuDeGiay") String tenKieuDeGiay
    );
}
