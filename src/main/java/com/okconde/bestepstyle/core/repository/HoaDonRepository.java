package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by TuanIf on 9/23/2024 22:48:53
 *
 * @author TuanIf
 */
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("""
                            select distinct hd from HoaDon hd where 
                            (:maHoaDon is null or hd.maHoaDon = :maHoaDon)
                            and
                            (:ngayTaoStart IS NULL OR hd.ngayTaoDon >= :ngayTaoStart)
                            and
                            (:ngayTaoEnd IS NULL OR hd.ngayTaoDon <= :ngayTaoEnd)
                            and
                            (:idKhachHang is null or hd.khachHang.idKhachHang = :idKhachHang)
                            and 
                            (:idNhanVien is null or hd.nhanVien.idNhanVien = :idNhanVien)
                            and 
                            (:idThanhToan is null or hd.thanhToan.idThanhToan = :idThanhToan)
                            and 
                            (:idPhieuGiamGia is null or hd.phieuGiamGia.idPhieuGiamGia = :idPhieuGiamGia)
            """)
    Page<HoaDon> searchPageHoaDon(Pageable pageable,
                                  @Param(value = "maHoaDon") String maHoaDon,
                                  @Param(value = "ngayTaoStart") Date ngayTaoStart,
                                  @Param(value = "ngayTaoEnd") Date ngayTaoEnd,
                                  @Param(value = "idKhachHang") Long idKhachHang,
                                  @Param(value = "idNhanVien") Long idNhanVien,
                                  @Param(value = "idThanhToan") Long idThanhToan,
                                  @Param(value = "idPhieuGiamGia") Long idPhieuGiamGia
    );
}
