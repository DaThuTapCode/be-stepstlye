package com.okconde.bestepstyle.core.dto.hoadon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangShortResponse;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienShortResponse;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;
import com.okconde.bestepstyle.core.dto.thanhtoan.response.ThanhToanResponse;
import com.okconde.bestepstyle.core.util.enumutil.LoaiHoaDon;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/25/2024 21:13:25
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonRequest {

    private Long idHoaDon;

    @NotBlank(message = "Mã Hóa Đơn không được để trống!")
    @Length(min = 5, message = "Mã Hóa Đơn phải lớn hơn 5 ký tự!")
    @Length(max = 10, message = "Mã Hóa Đơn không được vượt quá 10 ký tự!")
    private String maHoaDon;

    @NotNull(message = "Phí vận chuyeern không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phí vận chuyển phải lớn hơn 0!")
    private BigDecimal phiVanChuyen;

    @NotNull(message = "Tong tiền không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tổng tiền phải lớn hơn 0!")
    private BigDecimal tongTien;

    @NotNull(message = "Tong tiền sau giảm không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tong tiền sau giảm phải lớn hơn 0!")
    private BigDecimal tongTienSauGiam;

    @Enumerated(EnumType.STRING)
    private LoaiHoaDon loaiHoaDon;

    private String tenKhachHang;

    @NotBlank(message = "Địa chỉ giao hàng không được để trống!")
    @Length(min = 20, message = "Địa chỉ giao hàng phải lớn hơn 20 ký tự!")
    @Length(max = 255, message = "Địa chỉ giao hàng không được vượt quá 255 ký tự!")
    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTaoDon;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayXacNhan;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayNhanHang;

    @Enumerated(EnumType.STRING)
    private StatusHoaDon trangThai;

    private KhachHangShortResponse khachHang;

    private NhanVienShortResponse nhanVien;

    private ThanhToanResponse thanhToan;

    private PhieuGiamGiaResponse phieuGiamGia;


}
