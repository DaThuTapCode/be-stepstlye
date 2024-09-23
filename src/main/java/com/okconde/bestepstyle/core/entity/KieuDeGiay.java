package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumUtil.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created at 23/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kieu_de_giay")
public class KieuDeGiay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKieuDeGiay;

    private String tenKieuDeGiay;

    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
