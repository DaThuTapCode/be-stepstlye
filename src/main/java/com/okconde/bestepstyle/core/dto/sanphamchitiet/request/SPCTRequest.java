package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
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
    private Long idSpct;

    private String maSpct;

    private BigDecimal gia;

    private int soLuong;

    private StatusSPCT trangThai;

    private ChatLieuResponse chatLieu;

    private KieuDeGiayResponse kieuDeGiay;

    private ChatLieuDeGiayResponse chatLieuDeGiay;

    private TrongLuongResponse trongLuong;

    private MauSacResponse mauSac;

    private KichCoResponse kichCo;

    private List<Anh> anhs;
}
