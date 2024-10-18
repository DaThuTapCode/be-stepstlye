package com.okconde.bestepstyle.core.dto.mausac.request;

import lombok.*;

/**
 * Created at 10/10/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MauSacSearchRequest {

    private String maMauSac;

    private String tenMau;

}
