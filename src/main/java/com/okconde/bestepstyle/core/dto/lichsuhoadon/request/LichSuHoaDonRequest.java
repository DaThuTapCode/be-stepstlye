package com.okconde.bestepstyle.core.dto.lichsuhoadon.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * Created by TuanIf on 9/25/2024 21:14:16
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LichSuHoaDonRequest {

    private Long idLshd;

    @NotBlank(message = "Mã LSHĐ không được để trống!")
    @Length(min = 5, message = "Mã LSHĐ phải lớn hơn 5 ký tự!")
    @Length(max = 10, message = "Mã LSHĐ không được vượt quá 10 ký tự!")
    private String maLichSuHoaDon;

    private String hanhDong;

    @NotBlank(message = "Người thực hiện không được bỏ trống!")
    private String nguoiThucHien;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
