package com.okconde.bestepstyle.core.dto.khachhang.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 9/25/2024 21:33:47
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhachHangShortResponse {

    private Long idKhachHang;

    private String soDienThoai;

    private String email;

    private String ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
