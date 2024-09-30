package com.okconde.bestepstyle.core.dto.hoadon.request;

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

    private BigDecimal phiVanChuyen;

    private BigDecimal tongTien;

    private BigDecimal tongTienSauGiam;

    private String loaiHoaDon;

    private String tenKhachHang;

    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

}
