package com.okconde.bestepstyle.core.dto.thanhtoan.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.util.enumutil.StatusPTTT;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * Created by TuanIf on 9/25/2024 23:37:55
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ThanhToanRequest {

    private Long idThanhToan;

    @NotBlank(message = "Mã Thanh Toán không được để trống!")
    @Length(min = 5, message = "Mã Thanh Toán phải lớn hơn 5 ký tự!")
    @Length(max = 10, message = "Mã Thanh Toán không được vượt quá 10 ký tự!")
    private String maThanhToan;

    @NotBlank(message = "PTTT không được để trống!")
    @Enumerated(EnumType.STRING)
    private StatusPTTT phuongThucThanhToan;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
