package com.okconde.bestepstyle.core.dto.khachhang.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangShortResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 21:14:30
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhachHangRequest {

    private List<DiaChiKhachHangShortResponse> diaChiKhachHangs;

    private String tenKhachHang;

    private String soDienThoai;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

}
