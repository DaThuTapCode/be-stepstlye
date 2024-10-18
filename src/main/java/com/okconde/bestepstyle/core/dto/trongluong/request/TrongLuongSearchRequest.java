package com.okconde.bestepstyle.core.dto.trongluong.request;

import jakarta.validation.constraints.NotBlank;
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
public class TrongLuongSearchRequest {
    private String maTrongLuong;

    private String giaTri;
}
