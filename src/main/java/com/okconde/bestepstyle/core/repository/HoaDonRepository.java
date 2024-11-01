package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
            """)
    Page<HoaDon> searchPageHoaDon(Pageable pageable,
                                  @Param(value = "maHoaDon") String maHoaDon,
                                  @Param(value = "ngayTaoStart") Date ngayTaoStart,
                                  @Param(value = "ngayTaoEnd") Date ngayTaoEnd,
                                  @Param(value = "tenKhachHang") String tenKhachHang,
                                  @Param(value = "soDienThoai") String soDienThoai,
                                  StatusHoaDon trangThai
    );

    /**
     * Query select lấy hóa đơn theo trạng thái và loại hóa đơn
     * @param trangThai trạng thái của hóa đơn cần lấy
     * @param loaiHoaDon loại hóa đơn cần lấy
     * */
    @Query("""
        SELECT hd FROM HoaDon hd WHERE hd.trangThai = :trangThai AND hd.loaiHoaDon = :loaiHoaDon
    """)
    List<HoaDon> getHoaDonByStatus(StatusHoaDon trangThai, String loaiHoaDon);



    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE hd.trangThai = :trangThai")
    int countByStatus(StatusHoaDon trangThai);

}
