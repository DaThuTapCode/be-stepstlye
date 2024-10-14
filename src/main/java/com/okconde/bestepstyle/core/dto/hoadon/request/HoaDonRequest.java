package com.okconde.bestepstyle.core.dto.hoadon.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    private String maHoaDon;

    private BigDecimal phiVanChuyen;

    private BigDecimal tongTien;

    private BigDecimal tongTienSauGiam;

    private String loaiHoaDon;

    @NotBlank(message = "Tên Khách Hàng không được để trống")
    private String tenKhachHang;

    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

}
