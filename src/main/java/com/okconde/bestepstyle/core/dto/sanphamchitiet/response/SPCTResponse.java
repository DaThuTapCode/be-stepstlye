package com.okconde.bestepstyle.core.dto.sanphamchitiet.response;

import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import com.okconde.bestepstyle.core.dto.anh.response.AnhShortResponse;
import com.okconde.bestepstyle.core.dto.sanpham.response.SanPhamShortResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 21:14
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SPCTResponse {

    private Long idSpct;

    private BigDecimal gia;

    private int soLuong;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayChinhSua;

    private StatusSPCT trangThai;


    private SanPhamShortResponse sanPham;


    private ChatLieu chatLieu;


    private KieuDeGiay kieuDeGiay;


    private ChatLieuDeGiay chatLieuDeGiay;


    private TrongLuong trongLuong;


    private MauSac mauSac;


    private KichCo kichCo;


    private List<AnhShortResponse> anhs;
}
