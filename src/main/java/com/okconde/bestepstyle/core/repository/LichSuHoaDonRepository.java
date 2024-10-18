package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.LichSuHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by TuanIf on 9/23/2024 22:49:54
 *
 * @author TuanIf
 */
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Long> {
    @Query("""
                select distinct lshd from LichSuHoaDon lshd where (:maLichSuHoaDon is null or lshd.maLichSuHoaDon = :maLichSuHoaDon)
""")
    Page<LichSuHoaDon> searchPageLichSuHoaDon(Pageable pageable,
                                        @Param(value = "maLichSuHoaDon") String maLichSuHoaDon
    );
}

