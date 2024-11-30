package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Trong Phu on 23/09/2024 22:01
 *
 * @author Trong Phu
 */
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    @Query("""
                            select distinct spct from SanPhamChiTiet spct
                            where
                            (:tenSanPham is null or spct.sanPham.tenSanPham like %:tenSanPham%)
                            and
                            (:maSanPham is null or spct.sanPham.maSanPham like %:maSanPham%)
                            and
                            (:maSpct is null or spct.maSpct like %:maSpct%)
                            and
                            (:idMauSac is null or spct.mauSac.idMauSac = :idMauSac)
                            and
                            (:idKichCo is null or spct.kichCo.idKichCo = :idKichCo)
                            and
                            (:idChatLieu is null or spct.sanPham.chatLieu.idChatLieu = :idChatLieu)
                            and
                            (:idTrongLuong is null or spct.sanPham.trongLuong.idTrongLuong = :idTrongLuong)
                            and
                            (:idThuongHieu is null or spct.sanPham.thuongHieu.idThuongHieu = :idThuongHieu)
                            and
                            (:idDanhMuc is null or spct.sanPham.danhMuc.idDanhMuc = :idDanhMuc)
                            order by spct.ngayTao desc
                            """)
    Page<SanPhamChiTiet> searchPageSPCT(Pageable pageable,
                                  @Param(value = "tenSanPham") String tenSanPham,
                                  @Param(value = "maSanPham") String maSanPham,
                                  @Param(value = "maSpct") String maSpct,
                                  @Param(value = "idMauSac") Long idMauSac,
                                  @Param(value = "idKichCo") Long idKichCo,
                                  @Param(value = "idChatLieu") Long idChatLieu,
                                  @Param(value = "idTrongLuong") Long idTrongLuong,
                                  @Param(value = "idThuongHieu") Long idThuongHieu,
                                  @Param(value = "idDanhMuc") Long idDanhMuc
                                  );

    /**
     * Query lấy sản phẩm chi tiết theo id và trạng thái*/
    @Query("""
        SELECT spct FROM SanPhamChiTiet spct where spct.idSpct = :idSpct AND spct.trangThai  = :trangThai
    """)
    Optional<SanPhamChiTiet> getSPCTByIdSPCTAndTrangThai(Long idSpct, StatusSPCT trangThai);


    /**
     * Query lấy sản phẩm chi tiết theo danh sach id và trạng thái*/
    @Query("""
        SELECT spct FROM SanPhamChiTiet spct where spct.idSpct in :idSpcts AND spct.trangThai  = :trangThai
    """)
    List<SanPhamChiTiet> getSPCTByListIdSPCTAndTrangThai(List<Long> idSpcts, StatusSPCT trangThai);


    /**
     * Query lấy sản phẩm chi tiết theo id snả phẩm*/
    @Query("""
        SELECT spct FROM SanPhamChiTiet spct WHERE spct.sanPham.idSanPham = :idSanPham
    """)
    List<SanPhamChiTiet> getSPCTByIdSP(Long idSanPham);

    @Query("""
    SELECT COUNT(spct) > 0 FROM SanPhamChiTiet spct
    WHERE
        spct.sanPham.idSanPham = :idSanPham
    AND
        spct.kichCo.idKichCo = :idKichCo
    AND
        spct.mauSac.idMauSac = :idMauSac
""")
    Boolean checkExitsByAttribute(
            Long idSanPham,
            Long idKichCo,
            Long idMauSac
    );

    @Query("""
        select spct from SanPhamChiTiet spct where spct.idSpct in :listIdSpct and spct.trangThai = :trangThai
    """)
    List<SanPhamChiTiet> getSPCTByListIdAndTrangThai(List<Long> listIdSpct, StatusSPCT trangThai);


}


