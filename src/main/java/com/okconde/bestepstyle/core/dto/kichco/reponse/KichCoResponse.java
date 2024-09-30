package com.okconde.bestepstyle.core.dto.kichco.reponse;

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
public class KichCoResponse {
    private Long idKichCo;

    private Double giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
