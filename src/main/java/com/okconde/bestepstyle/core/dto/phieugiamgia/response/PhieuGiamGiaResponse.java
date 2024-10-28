package com.okconde.bestepstyle.core.dto.phieugiamgia.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayBatDau;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayKetThuc;

    private BigDecimal giaTriGiamToiDa;

    private BigDecimal giaTriGiamToiThieu;

    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusPhieuGiamGia trangThai;

}
