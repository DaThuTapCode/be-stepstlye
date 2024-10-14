package com.okconde.bestepstyle.core.dto.hoadon.response;

import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangResponse;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangShortResponse;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienShortResponse;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.entity.PhieuGiamGia;
import com.okconde.bestepstyle.core.entity.ThanhToan;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/25/2024 21:36:48
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonShortResponse {

    private Long idHoaDon;

    private String maHoaDon;

    private LocalDateTime ngayTaoDon;

    private BigDecimal phiVanChuyen;

    private BigDecimal tongTien;

    private BigDecimal tongTienSauGiam;

    private LocalDateTime ngayChinhSua;

    private LocalDateTime ngayXacNhan;

    private LocalDateTime ngayNhanHang;

    private String loaiHoaDon;

    private String tenKhachHang;

    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private StatusHoaDon trangThai;

    private KhachHang khachHang;

    private NhanVien nhanVien;

    private ThanhToan thanhToan;

    private PhieuGiamGia phieuGiamGia;
}
