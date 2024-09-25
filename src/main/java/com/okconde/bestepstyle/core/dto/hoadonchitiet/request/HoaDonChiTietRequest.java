package com.okconde.bestepstyle.core.dto.hoadonchitiet.request;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/25/2024 21:13:49
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonChiTietRequest {

    private int soLuong;

    private BigDecimal donGia;

    private BigDecimal tongTien;

}
