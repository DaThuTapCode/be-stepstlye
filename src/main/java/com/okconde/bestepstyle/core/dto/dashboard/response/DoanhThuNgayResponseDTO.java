package com.okconde.bestepstyle.core.dto.dashboard.response;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Trong Phu on 30/11/2024 11:32
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoanhThuNgayResponseDTO {
    private int ngay;
    private BigDecimal doanhThu;
}
