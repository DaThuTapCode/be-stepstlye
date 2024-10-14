package com.okconde.bestepstyle.core.dto.danhmuc.request;

import lombok.*;


/**
 * Created by Trong Phu on 25/09/2024 21:12
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanhMucRequest {

    private Long idDanhMuc;

    private String maDanhMuc;

    private String tenDanhMuc;

    private String moTa;

}
