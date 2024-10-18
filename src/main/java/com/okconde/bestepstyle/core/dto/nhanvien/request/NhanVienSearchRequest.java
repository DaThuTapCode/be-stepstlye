package com.okconde.bestepstyle.core.dto.nhanvien.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 10/15/2024 09:43:40
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienSearchRequest {

    private String maNhanVien;

    private String hoTen;

    private String soDienThoai;

}
