package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusLoaiGiam;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by TuanIf on 9/23/2024 21:31:04
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhieuGiamGia;

    private String maPhieuGiamGia;

    private String tenPhieuGiamGia;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusLoaiGiam loaiGiam;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private BigDecimal giaTriGiamToiDa;

    private BigDecimal giaTriHoaDonToiThieu;

    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusPhieuGiamGia trangThai;
}
