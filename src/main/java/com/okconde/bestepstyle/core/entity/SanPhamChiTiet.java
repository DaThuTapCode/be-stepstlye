package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Trong Phu on 23/09/2024 21:28
 *
 * @author Trong Phu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpct;

    private String maSpct;

    private BigDecimal gia;

    private int soLuong;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayChinhSua;

    private String anh;

    @Enumerated(EnumType.STRING)
    private StatusSPCT trangThai;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "id_kich_co")
    private KichCo kichCo;

}
