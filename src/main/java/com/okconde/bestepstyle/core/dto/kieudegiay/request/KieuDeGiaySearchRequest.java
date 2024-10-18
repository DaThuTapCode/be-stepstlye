package com.okconde.bestepstyle.core.dto.kieudegiay.request;

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
public class KieuDeGiaySearchRequest {
    private String maKieuDeGiay;

    private String tenKieuDeGiay;
}
