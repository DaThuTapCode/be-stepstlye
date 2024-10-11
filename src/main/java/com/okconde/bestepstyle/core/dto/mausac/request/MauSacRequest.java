package com.okconde.bestepstyle.core.dto.mausac.request;

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
public class MauSacRequest {

    @NotBlank(message = "Tên màu sắc không được để trống!")
    @Length(max = 255, message = "Tên màu sắc không được vượt quá 255 ký tự!")
    @Length(min = 2, message = "Tên màu sắc phải từ 2 ký tự trở lên!")
    private String tenMau;

    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
