package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by TuanIf on 9/23/2024 21:30:53
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class LichSuHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLshd;

    private String maLichSuHoaDon;

    private String hanhDong;

    private LocalDateTime ngayTao;

    private String nguoiThucHien;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;
}
