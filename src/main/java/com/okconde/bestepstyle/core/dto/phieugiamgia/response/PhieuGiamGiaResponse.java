package com.okconde.bestepstyle.core.dto.phieugiamgia.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
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

    private String maPhieuGiamGia;

    private String tenPhieuGiamGia;

    private String moTa;

    private String loaiGiam;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayBatDau;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayKetThuc;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private BigDecimal giaTriGiamToiDa;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private BigDecimal giaTriGiamToiThieu;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusPhieuGiamGia trangThai;

}
