package com.okconde.bestepstyle.core.dto.phieugiamgia.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * Created by TuanIf on 10/16/2024 17:13:51
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhieuGiamGiaSearchRequest {

    private String maPhieuGiamGia;

    private String tenPhieuGiamGia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayBatDau;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayKetThuc;

    private String loaiGiam;

}
