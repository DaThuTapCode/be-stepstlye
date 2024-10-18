package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by TuanIf on 9/23/2024 22:50:21
 *
 * @author TuanIf
 */
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {
    @Query("""
                select distinct pgg from PhieuGiamGia pgg where
                 (:maPhieuGiamGia is null or pgg.maPhieuGiamGia = :maPhieuGiamGia)
                and
                 (:tenPhieuGiamGia is null or pgg.tenPhieuGiamGia = :tenPhieuGiamGia)
                and
                 (:ngayBatDau IS NULL OR pgg.ngayBatDau >= :ngayBatDau)
                and
                 (:ngayKetThuc IS NULL OR pgg.ngayKetThuc <= :ngayKetThuc)
                and
                 (:loaiGiam IS NULL OR pgg.loaiGiam = :loaiGiam)
""")
    Page<PhieuGiamGia> searchPagePhieuGiamGia(Pageable pageable,
                                        @Param(value = "maPhieuGiamGia") String maPhieuGiamGia,
                                        @Param(value = "tenPhieuGiamGia") String tenPhieuGiamGia,
                                        @Param(value = "ngayBatDau") Date ngayBatDau,
                                        @Param(value = "ngayKetThuc") Date ngayKetThuc,
                                        @Param(value = "loaiGiam") String loaiGiam
    );
}
