package com.okconde.bestepstyle.core.dto.thanhtoan.request;

import lombok.*;

/**
 * Created by TuanIf on 10/16/2024 16:57:05
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ThanhToanSearchRequest {

    private String maThanhToan;

    private String phuongThucThanhToan;
}
