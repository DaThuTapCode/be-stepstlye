package com.okconde.bestepstyle.core.dto.khachhang.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 10/9/2024 20:30:54
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhachHangSearchRequest {

    private String maKhachHang;

    private String tenKhachHang;

    private String soDienThoai;
}
