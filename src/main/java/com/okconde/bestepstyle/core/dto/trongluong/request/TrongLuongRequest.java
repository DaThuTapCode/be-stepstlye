package com.okconde.bestepstyle.core.dto.trongluong.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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
public class TrongLuongRequest {

    @NotBlank(message = "Giá trị trọng lượng không được để trống!")
    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
