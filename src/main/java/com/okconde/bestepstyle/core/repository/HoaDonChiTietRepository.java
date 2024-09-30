package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by TuanIf on 9/23/2024 22:49:25
 *
 * @author TuanIf
 */
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {

    @Query("""
            select hdct from HoaDonChiTiet hdct where hdct.hoaDon.idHoaDon = :idHoaDon
            """)
    List<HoaDonChiTiet> findHoaDonChiTietByIdHoaDon(
            @Param (value = "idHoaDon") Long idHoaDon
    );
}
