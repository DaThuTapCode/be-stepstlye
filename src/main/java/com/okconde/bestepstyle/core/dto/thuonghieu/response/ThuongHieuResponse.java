package com.okconde.bestepstyle.core.dto.thuonghieu.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by Trong Phu on 25/09/2024 21:14
 *
 * @author Trong Phu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieuResponse {

    private Long idThuongHieu;

    private String tenThuongHieu;

    private String xuatXu;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
