package com.okconde.bestepstyle.core.dto.thanhtoan.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Created by TuanIf on 9/25/2024 23:37:55
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ThanhToanRequest {

    private Long idThanhToan;

    private String maThanhToan;

    private String phuongThucThanhToan;

}
