package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by TuanIf on 9/23/2024 21:52:04
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idThanhToan;
    private String maThanhToan;
    private String phuongThucThanhToan;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
