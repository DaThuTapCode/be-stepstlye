package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by TuanIf on 9/23/2024 22:49:25
 *
 * @author TuanIf
 */
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {

    @Query("""
            select hdct from HoaDonChiTiet hdct where hdct.hoaDon.idHoaDon = :idHoaDon order by hdct.idHoaDonChiTiet desc
            """)
    List<HoaDonChiTiet> findHoaDonChiTietByIdHoaDon(
            @Param (value = "idHoaDon") Long idHoaDon
    );


    @Query("""
                select distinct hdct from HoaDonChiTiet hdct where (:soLuong is null or hdct.soLuong = :soLuong)
                and (:maHoaDonChiTiet is null or hdct.maHoaDonChiTiet = :maHoaDonChiTiet) order by hdct.idHoaDonChiTiet desc
""")
    Page<HoaDonChiTiet> searchPageHoaDonChiTiet(Pageable pageable,
                                                @Param(value = "soLuong") Integer soLuong,
                                                @Param(value = "maHoaDonChiTiet") String maHoaDonChiTiet
                                                );


}
