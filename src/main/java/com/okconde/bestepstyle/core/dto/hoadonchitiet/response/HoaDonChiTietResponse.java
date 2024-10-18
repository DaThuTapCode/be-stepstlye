package com.okconde.bestepstyle.core.dto.hoadonchitiet.response;

import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamShortResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTShortResponse;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by TuanIf on 9/25/2024 21:14:00
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonChiTietResponse {

    private Long idHoaDonChiTiet;

    private String maHoaDonChiTiet;

    private int soLuong;

    private BigDecimal donGia;

    private BigDecimal tongTien;

    @Enumerated(EnumType.STRING)
    private StatusHoaDonChiTiet trangThai;

    private SPCTShortResponse sanPhamChiTiet;

}
