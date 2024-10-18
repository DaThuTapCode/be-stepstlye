package com.okconde.bestepstyle.core.dto.kichco.request;

import lombok.*;

/**
 * Created at 17/10/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KichCoSearchRequest {
    private String maKichCo;

    private Double giaTri;
}
