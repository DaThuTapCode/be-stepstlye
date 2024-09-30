package com.okconde.bestepstyle.core.dto.lichsuhoadon.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/25/2024 21:14:28
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LichSuHoaDonResponse {

    private Long idLshd;

    private String hanhDong;

    private LocalDateTime ngayTao;

    private String nguoiThucHien;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private HoaDonShortResponse hoaDon;
}
