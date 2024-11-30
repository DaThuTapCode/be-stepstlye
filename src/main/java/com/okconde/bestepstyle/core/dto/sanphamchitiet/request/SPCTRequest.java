package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.dto.kichco.request.KichCoRequest;
import com.okconde.bestepstyle.core.dto.mausac.request.MauSacRequest;
import com.okconde.bestepstyle.core.dto.sanpham.request.SanPhamRequest;

import lombok.*;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

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
    private Long idSpct;

    private String maSpct;

    private BigDecimal gia;

    private int soLuong;

    private SanPhamRequest sanPham;

    private MauSacRequest mauSac;

    private KichCoRequest kichCo;

    private MultipartFile anhFile;
}
