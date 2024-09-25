package com.okconde.bestepstyle.core.dto.thuonghieu.request;

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
public class ThuongHieuRequest {

    private String tenThuongHieu;

    private String xuatXu;

    private String moTa;

}
