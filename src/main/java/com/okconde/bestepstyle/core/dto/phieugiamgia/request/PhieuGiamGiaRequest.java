package com.okconde.bestepstyle.core.dto.phieugiamgia.request;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/25/2024 21:14:40
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhieuGiamGiaRequest {


    private String tenPhieuGiamGia;

    private String moTa;

    private String loaiGiam;

    private BigDecimal giaTriGiamToiDa;

    private BigDecimal giaTriGiamToiThieu;

    private BigDecimal giaTriGiam;

}
