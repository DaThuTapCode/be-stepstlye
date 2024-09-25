package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 21:13
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SPCTRequest {

    private BigDecimal gia;

    private int soLuong;

    private StatusSPCT trangThai;

    private SanPham sanPham;

    private ChatLieu chatLieu;

    private KieuDeGiay kieuDeGiay;

    private ChatLieuDeGiay chatLieuDeGiay;

    private TrongLuong trongLuong;

    private MauSac mauSac;

    private KichCo kichCo;

    private List<Anh> anhs;
}
