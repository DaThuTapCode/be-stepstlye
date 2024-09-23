package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
