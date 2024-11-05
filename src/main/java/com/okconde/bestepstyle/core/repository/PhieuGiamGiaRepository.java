package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                and
                 (:trangThai IS NULL OR pgg.trangThai = :trangThai)
                order by pgg.idPhieuGiamGia desc
""")
    Page<PhieuGiamGia> searchPagePhieuGiamGia(Pageable pageable,
                                              @Param(value = "maPhieuGiamGia") String maPhieuGiamGia,
                                              @Param(value = "tenPhieuGiamGia") String tenPhieuGiamGia,
                                              @Param(value = "ngayBatDau") Date ngayBatDau,
                                              @Param(value = "ngayKetThuc") Date ngayKetThuc,
                                              @Param(value = "loaiGiam") String loaiGiam,
                                              StatusPhieuGiamGia trangThai);

    @Query("SELECT COUNT(pgg) FROM PhieuGiamGia pgg WHERE pgg.trangThai = :trangThai")
    int countByStatus(StatusPhieuGiamGia trangThai);

    @Query("SELECT pgg FROM PhieuGiamGia pgg WHERE pgg.trangThai = :trangThai AND pgg.ngayKetThuc < :today")
    List<PhieuGiamGia> findByTrangThaiAndNgayKetThucBefore(
            StatusPhieuGiamGia trangThai,
            @Param("today") LocalDate today);

    @Query("""
                select pgg from PhieuGiamGia pgg where pgg.idPhieuGiamGia = :idPhieuGiamGia and pgg.trangThai = :trangThai
""")
    Optional<PhieuGiamGia> searchPGGTheoIDVaTrangThai(Long idPhieuGiamGia, StatusPhieuGiamGia trangThai);
}
