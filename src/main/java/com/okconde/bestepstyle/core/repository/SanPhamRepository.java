package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamSearchRequest;
import com.okconde.bestepstyle.core.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by Trong Phu on 23/09/2024 22:01
 *
 * @author Trong Phu
 */
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    @Query("""
            SELECT sp FROM  SanPham sp where
            (:tenSanPham IS NUll OR sp.tenSanPham like  %:tenSanPham%)
            AND 
            (:ngayTaoStart IS NULL OR sp.ngayTao >= :ngayTaoStart)
            AND 
            (:ngayTaoEnd IS NULL OR sp.ngayTao <= :ngayTaoEnd)
            AND 
            (:idDanhMuc IS NULL OR sp.danhMuc.idDanhMuc = :idDanhMuc)
            AND 
            (:idThuongHieu IS NULL OR sp.thuongHieu.idThuongHieu = :idThuongHieu)
            """)
    Page<SanPham> searchPage(Pageable pageable,
                             @Param("tenSanPham") String tenSanPham,
                             @Param("ngayTaoStart") Date ngayTaoStart,
                             @Param("ngayTaoEnd") Date ngayTaoEnd,
                             @Param("idDanhMuc") Long idDanhMuc,
                             @Param("idThuongHieu") Long idThuongHieu
    );
}
