package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.entity.SanPham;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Trong Phu on 23/09/2024 22:01
 *
 * @author Trong Phu
 */
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query("""
            SELECT DISTINCT sp FROM  SanPham sp
            LEFT JOIN FETCH sp.sanPhamChiTiets
            where
            (:maSanPham IS NUll OR sp.maSanPham like  %:maSanPham%)
            AND
            (:tenSanPham IS NUll OR sp.tenSanPham like  %:tenSanPham%)
            AND
            (:ngayTaoStart IS NULL OR sp.ngayTao >= :ngayTaoStart)
            AND
            (:ngayTaoEnd IS NULL OR sp.ngayTao <= :ngayTaoEnd)
            AND
            (:idDanhMuc IS NULL OR sp.danhMuc.idDanhMuc = :idDanhMuc)
            AND
            (:idThuongHieu IS NULL OR sp.thuongHieu.idThuongHieu = :idThuongHieu)
            ORDER BY sp.idSanPham desc
            """)
    Page<SanPham> searchPageSanPham(Pageable pageable,
                             @Param("maSanPham") String maSanPham,
                             @Param("tenSanPham") String tenSanPham,
                             @Param("ngayTaoStart") Date ngayTaoStart,
                             @Param("ngayTaoEnd") Date ngayTaoEnd,
                             @Param("idDanhMuc") Long idDanhMuc,
                             @Param("idThuongHieu") Long idThuongHieu
    );

    /**
     * Tìm sản phẩm theo mã*/
    @Query("""
        SELECT sp FROM SanPham sp WHERE sp.maSanPham like :maSanPham
    """)
    Optional<SanPham> getSanPhamByMaSanPham(String maSanPham);

    /**
     * Tìm sản phẩm theo tên*/
    @Query("""
        SELECT sp FROM SanPham sp WHERE sp.tenSanPham like :tenSanPham
    """)
    Optional<SanPham> getSanPhamByTenSanPham(String tenSanPham);

    @Query("""
        SELECT sp FROM SanPham sp WHERE sp.idSanPham = :idSanPham and sp.trangThai = :trangThai
    """)
    Optional<SanPham> getSanPhamByIdAndTrangThai(Long idSanPham, StatusSP trangThai);
}
