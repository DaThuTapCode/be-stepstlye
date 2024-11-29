package com.okconde.bestepstyle.core.dto.hoadon.request;

import com.okconde.bestepstyle.core.dto.hoadonchitiet.request.HoaDonChiTietBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.khachhang.request.KhachHangRequest;
import com.okconde.bestepstyle.core.dto.phieugiamgia.request.PhieuGiamGiaRequest;
import com.okconde.bestepstyle.core.util.enumutil.StatusPTTT;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Trong Phu on 19/11/2024 23:06
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonBanOnlineRequest {

    private List<HoaDonChiTietBanOnlineRequest> hoaDonChiTiets;

    private KhachHangRequest khachHang;

    private PhieuGiamGiaRequest phieuGiamGia;

    private BigDecimal phiVanChuyen;

    private BigDecimal tongTien;

    private BigDecimal tongTienSauGiam;

    private String tenKhachHang;

    private String diaChiGiaoHang;

    private String soDienThoaiKhachHang;

    private String ghiChu;

    private StatusPTTT thanhToan;
}
