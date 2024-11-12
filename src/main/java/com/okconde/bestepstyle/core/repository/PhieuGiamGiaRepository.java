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
    // Phân trang và tìm kiếm phiếu giảm giá với các điều kiện khác nhau
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

    // Đếm số lượng phiếu giảm giá theo trạng thái
    @Query("SELECT COUNT(pgg) FROM PhieuGiamGia pgg WHERE pgg.trangThai = :trangThai")
    int countByStatus(StatusPhieuGiamGia trangThai);

    // Tìm phiếu giảm giá có trạng thái ACTIVE với ngày kết thúc trước ngày hiện tại
    @Query("SELECT pgg FROM PhieuGiamGia pgg WHERE pgg.trangThai = :trangThai AND pgg.ngayKetThuc < :today")
    List<PhieuGiamGia> findByTrangThaiAndNgayKetThucBefore(
            StatusPhieuGiamGia trangThai,
            @Param("today") LocalDate today);

    // Tìm phiếu giảm giá có trạng thái COMINGSOON và ngày bắt đầu bằng ngày hiện tại
    @Query("SELECT pgg FROM PhieuGiamGia pgg WHERE pgg.trangThai = :trangThai AND pgg.ngayBatDau = :today")
    List<PhieuGiamGia> findByTrangThaiAndNgayBatDau(
            StatusPhieuGiamGia trangThai,
            @Param("today") LocalDate today);

    // Tìm phiếu giảm giá theo ID và trạng thái
    @Query("""
                select pgg from PhieuGiamGia pgg where pgg.idPhieuGiamGia = :idPhieuGiamGia and pgg.trangThai = :trangThai
""")
    Optional<PhieuGiamGia> findByPhieuGiamGiaAndTrangThai(Long idPhieuGiamGia, StatusPhieuGiamGia trangThai);


}
