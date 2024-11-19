package com.okconde.bestepstyle.core.dto.thanhtoan.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusPTTT;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Created by TuanIf on 9/25/2024 23:38:34
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ThanhToanResponse {

    private Long idThanhToan;

    private String maThanhToan;

    @Enumerated(EnumType.STRING)
    private StatusPTTT phuongThucThanhToan;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
