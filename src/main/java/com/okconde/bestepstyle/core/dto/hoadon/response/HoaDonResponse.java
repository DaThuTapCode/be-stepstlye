package com.okconde.bestepstyle.core.dto.hoadon.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.hoadonchitiet.response.HoaDonChiTietResponse;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangShortResponse;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienShortResponse;
import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 21:13:35
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonResponse {

    private Long idHoaDon;

    private String maHoaDon;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTaoDon;

    private BigDecimal phiVanChuyen;

    private BigDecimal tongTien;

    private BigDecimal tongTienSauGiam;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayXacNhan;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayNhanHang;


    private String loaiHoaDon;


    private String tenKhachHang;

    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;


    private String ghiChu;


    @Enumerated(EnumType.STRING)
    private StatusHoaDon trangThai;

    private KhachHangShortResponse khachHang;

    private NhanVienShortResponse nhanVien;

    private ThanhToanResponse thanhToan;

    private PhieuGiamGiaResponse phieuGiamGia;

    private List<HoaDonChiTietResponse> hoaDonChiTiet;
}
