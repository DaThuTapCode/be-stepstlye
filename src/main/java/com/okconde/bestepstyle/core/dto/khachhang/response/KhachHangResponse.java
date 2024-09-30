package com.okconde.bestepstyle.core.dto.khachhang.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 21:14:42
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhachHangResponse {

    private Long idKhachHang;

    private String tenKhachHang;

    private String soDienThoai;

    private String email;

    private String ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private List<DiaChiKhachHangShortResponse> diaChiKhachHangs;

}
