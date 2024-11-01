package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/23/2024 21:30:28
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoaDonChiTiet;

    private String maHoaDonChiTiet;

    private int soLuong;

    private BigDecimal donGia;

    private BigDecimal tongTien;

    @Enumerated(EnumType.STRING)
    private StatusHoaDonChiTiet trangThai;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_spct")
    private SanPhamChiTiet sanPhamChiTiet;
}
