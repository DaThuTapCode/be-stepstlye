package com.okconde.bestepstyle.core.dto.lichsuhoadon.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by TuanIf on 10/18/2024 21:00:18
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LichSuHoaDonShortResponse {

    private Long idLshd;

    private String maLichSuHoaDon;

    private String hanhDong;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    private String nguoiThucHien;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
