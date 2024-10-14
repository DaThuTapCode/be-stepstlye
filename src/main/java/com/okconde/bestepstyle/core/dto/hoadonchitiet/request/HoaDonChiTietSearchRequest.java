package com.okconde.bestepstyle.core.dto.hoadonchitiet.request;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 10/11/2024 19:38:40
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonChiTietSearchRequest {

    private int soLuong;

    private String maHoaDonChiTiet;

    private BigDecimal tongTien;
}
