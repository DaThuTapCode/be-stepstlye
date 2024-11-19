package com.okconde.bestepstyle.core.dto.thuonghieu.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Created at 19/11/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuSearchRequest {
    private String maThuongHieu;

    private String tenThuongHieu;
}
