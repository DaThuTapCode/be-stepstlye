package com.okconde.bestepstyle.core.dto.trongluong.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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
public class TrongLuongRequest {
    private Long idTrongLuong;

    private String maTrongLuong;

    @NotBlank(message = "Giá trị trọng lượng không được để trống!")
    @Length(max = 255, message = "Giá trị trọng lượng không được vượt quá 255 ký tự!")
    @Length(min = 1, message = "Giá trị trọng lượng phải từ 1 ký tự trở lên!")
    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
