package com.okconde.bestepstyle.core.dto.danhmuc.request;

import lombok.*;

/**
 * Created at 19/11/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanhMucSearchRequest {
    private String maDanhMuc;

    private String tenDanhMuc;
}
