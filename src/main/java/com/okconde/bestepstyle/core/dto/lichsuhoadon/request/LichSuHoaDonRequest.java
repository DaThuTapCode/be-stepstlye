package com.okconde.bestepstyle.core.dto.lichsuhoadon.request;

import lombok.*;

/**
 * Created by TuanIf on 9/25/2024 21:14:16
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LichSuHoaDonRequest {

    private Long idLshd;

    private String maLichSuHoaDon;

    private String hanhDong;

    private String nguoiThucHien;

}
