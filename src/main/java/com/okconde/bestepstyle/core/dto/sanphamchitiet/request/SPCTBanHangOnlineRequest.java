package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamShortResponse;
import com.okconde.bestepstyle.core.entity.KichCo;
import com.okconde.bestepstyle.core.entity.MauSac;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Trong Phu on 20/11/2024 22:22
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SPCTBanHangOnlineRequest {
    private Long idSpct;

    private String maSpct;

    private BigDecimal gia;

    private int soLuong;

    private String anh;

    private StatusSPCT trangThai;

    private SanPhamShortResponse sanPham;

    private MauSac mauSac;

    private KichCo kichCo;
}
