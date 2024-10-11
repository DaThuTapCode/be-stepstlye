package com.okconde.bestepstyle.core.dto.mausac.reponse;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MauSacResponse {
    private Long idMauSac;

    private String maMauSac;

    private String tenMau;

    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
