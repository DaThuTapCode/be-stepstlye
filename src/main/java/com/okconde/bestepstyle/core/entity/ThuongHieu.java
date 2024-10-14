package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by Trong Phu on 23/09/2024 21:29
 *
 * @author Trong Phu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thuong_hieu")
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idThuongHieu;

    private String maThuongHieu;

    private String tenThuongHieu;

    private String xuatXu;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
