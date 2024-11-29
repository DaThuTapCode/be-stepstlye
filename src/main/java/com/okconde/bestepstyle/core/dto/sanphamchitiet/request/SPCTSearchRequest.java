package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.chatlieudegiay.response.ChatLieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.kichco.reponse.KichCoResponse;
import com.okconde.bestepstyle.core.dto.kieudegiay.reponse.KieuDeGiayResponse;
import com.okconde.bestepstyle.core.dto.mausac.reponse.MauSacResponse;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
import lombok.*;

/**
 * Created at 31/10/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SPCTSearchRequest {

    private String tenSanPham;

    private String maSanPham;

    private String maSpct;

    private Long idSanPham;

    private Long idMauSac;

    private Long idKichCo;

    private Long idChatLieu;

    private Long idTrongLuong;

    private Long idThuongHieu;

    private Long idDanhMuc;
}
