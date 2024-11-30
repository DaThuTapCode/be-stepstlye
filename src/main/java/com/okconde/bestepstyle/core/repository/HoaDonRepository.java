package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.core.util.enumutil.LoaiHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                            (:tenKhachHang is null or hd.khachHang.tenKhachHang = :tenKhachHang)
                            and 
                            (:soDienThoai is null or hd.khachHang.soDienThoai = :soDienThoai)
                            and
                            (:trangThai is null or hd.trangThai = :trangThai)
                            and
                            (:loaiHoaDon is null or hd.loaiHoaDon = :loaiHoaDon)
                            order by hd.ngayTaoDon desc
            """)
    Page<HoaDon> searchPageHoaDon(Pageable pageable,
                                  @Param(value = "maHoaDon") String maHoaDon,
                                  @Param(value = "ngayTaoStart") Date ngayTaoStart,
                                  @Param(value = "ngayTaoEnd") Date ngayTaoEnd,
                                  @Param(value = "tenKhachHang") String tenKhachHang,
                                  @Param(value = "soDienThoai") String soDienThoai,
                                  StatusHoaDon trangThai,
                                  LoaiHoaDon loaiHoaDon);

    /**
     * Query select lấy hóa đơn theo trạng thái và loại hóa đơn
     * @param trangThai trạng thái của hóa đơn cần lấy
     * @param loaiHoaDon loại hóa đơn cần lấy
     * */
    @Query("""
        SELECT hd FROM HoaDon hd WHERE hd.trangThai = :trangThai AND hd.loaiHoaDon = :loaiHoaDon
    """)
    List<HoaDon> getHoaDonByStatus(StatusHoaDon trangThai, LoaiHoaDon loaiHoaDon);



    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE hd.trangThai = :trangThai")
    int countByStatus(StatusHoaDon trangThai);

    /**
     * Query lấy hóa đơn theo id và trạng thái*/
    @Query("""
        SELECT hd FROM HoaDon hd WHERE hd.idHoaDon = :idHoaDon AND hd.trangThai = :trangThai
    """)
    Optional<HoaDon> findByIdHoaDonAndTrangThai(Long idHoaDon, StatusHoaDon trangThai);

    @Query("SELECT h FROM HoaDon h WHERE h.phieuGiamGia = :phieuGiamGia AND h.trangThai = :trangThai")
    List<HoaDon> findByPhieuGiamGiaAndTrangThai(@Param("phieuGiamGia") PhieuGiamGia phieuGiamGia,
                                                         @Param("trangThai") StatusHoaDon trangThai);

    @Query("""
SELECT h FROM HoaDon h WHERE h.loaiHoaDon = :loaiHoaDon
""")
    List<HoaDon> getHoaDon(LoaiHoaDon loaiHoaDon);


    /** Lay danh sach hoa don theo khach hang*/
    @Query("""
                SELECT hd FROM HoaDon  hd where hd.khachHang.maKhachHang = :maKH order by hd.ngayTaoDon desc
            """)
    List<HoaDon> getListHoaDonByKhachHang(@Param("maKH") String maKh);




    /**
     *  Query lay tong doanh thu ngay hom nay
     * */
    @Query("""
    SELECT hd FROM HoaDon hd 
    WHERE hd.trangThai = :trangThai 
      AND hd.ngayTaoDon >= :startOfDay 
      AND hd.ngayTaoDon < :endOfDay
""")
    List<HoaDon> layHoaDonTheoTrangThaiVaNgay(
            @Param("trangThai") StatusHoaDon trangThai,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

/**
 * Query lấy doanh thu theo tháng hiện tại*/
    @Query("""
    SELECT hd FROM HoaDon hd 
    WHERE hd.trangThai = :trangThai 
      AND hd.ngayTaoDon >= :startOfMonth 
      AND hd.ngayTaoDon < :endOfMonth
""")
    List<HoaDon> layHoaDonTheoTrangThaiVaThang(
            @Param("trangThai") StatusHoaDon trangThai,
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth
    );


    /**
     * Query lấy doanh thu theo năm hiện tại
     * */
    @Query("""
    SELECT hd FROM HoaDon hd 
    WHERE hd.trangThai = :trangThai 
      AND hd.ngayTaoDon >= :startOfYear 
      AND hd.ngayTaoDon < :endOfYear
""")
    List<HoaDon> layHoaDonTheoTrangThaiVaNam(
            @Param("trangThai") StatusHoaDon trangThai,
            @Param("startOfYear") LocalDateTime startOfYear,
            @Param("endOfYear") LocalDateTime endOfYear
    );

    /**
     * Query lấy tổng doanh thu từng ngày trong một tháng/năm
     */
    @Query("""
    SELECT 
        FUNCTION('DAY', hd.ngayTaoDon) AS ngay,
        SUM(hd.tongTienSauGiam + hd.phiVanChuyen) AS doanhThu
    FROM HoaDon hd
    WHERE hd.trangThai = :trangThai 
      AND hd.ngayTaoDon >= :startOfMonth 
      AND hd.ngayTaoDon < :endOfMonth
    GROUP BY FUNCTION('DAY', hd.ngayTaoDon)
    ORDER BY ngay
""")
    List<Object[]> layDoanhThuTheoTungNgayTrongThang(
            @Param("trangThai") StatusHoaDon trangThai,
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth
    );


}
