package com.okconde.bestepstyle.core.dto.hoadon.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

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
    @DecimalMin(value = "0.0", inclusive = false, message = "Phí vận chuyeern > 0!")
    private BigDecimal phiVanChuyen;

    @NotNull(message = "Tong tiền không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tong tiền > 0!")
    private BigDecimal tongTien;

    @NotNull(message = "Tong tiền sau giảm không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tong tiền sau giảm > 0!")
    private BigDecimal tongTienSauGiam;

    @NotBlank(message = "Loại Hóa Đơn không được để trống!")
    @Length(min = 5, message = "Loại Hóa Đơn phải lớn hơn 5 ký tự!")
    @Length(max = 20, message = "Loại Hóa Đơn không được vượt quá 20 ký tự!")
    private String loaiHoaDon;

    private String tenKhachHang;

    @NotBlank(message = "Địa chỉ giao hàng không được để trống!")
    @Length(min = 20, message = "Địa chỉ giao hàng phải lớn hơn 20 ký tự!")
    @Length(max = 255, message = "Địa chỉ giao hàng không được vượt quá 255 ký tự!")
    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private StatusHoaDon trangThai;

}
