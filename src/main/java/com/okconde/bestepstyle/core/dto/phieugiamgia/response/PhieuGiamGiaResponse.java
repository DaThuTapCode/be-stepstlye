package com.okconde.bestepstyle.core.dto.phieugiamgia.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/25/2024 21:14:50
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhieuGiamGiaResponse {

    private Long idPhieuGiamGia;
    private String tenPhieuGiamGia;
    private String moTa;
    private String loaiGiam;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private BigDecimal giaTriGiamToiDa;
    private BigDecimal giaTriGiamToiThieu;
    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
