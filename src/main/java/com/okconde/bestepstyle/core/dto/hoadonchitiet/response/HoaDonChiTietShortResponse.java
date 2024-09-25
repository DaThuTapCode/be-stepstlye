package com.okconde.bestepstyle.core.dto.hoadonchitiet.response;

import com.okconde.bestepstyle.core.entity.HoaDon;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/25/2024 21:43:50
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonChiTietShortResponse {

    private Long idHoaDonChiTiet;

    private int soLuong;

    private BigDecimal donGia;

    private BigDecimal tongTien;

    @Enumerated(EnumType.STRING)
    private StatusHoaDonChiTiet trangThai;


    private HoaDon hoaDon;

    private SanPhamChiTiet sanPhamChiTiet;
}
