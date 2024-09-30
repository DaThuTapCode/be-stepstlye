package com.okconde.bestepstyle.core.entity;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
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
@Table(name = "trong_luong")
public class TrongLuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrongLuong;

    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
