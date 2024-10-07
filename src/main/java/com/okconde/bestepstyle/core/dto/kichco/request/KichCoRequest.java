package com.okconde.bestepstyle.core.dto.kichco.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
public class KichCoRequest {

    @NotBlank(message = "Giá trị kích cỡ không được để trống!")
    private Double giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
