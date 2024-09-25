package com.okconde.bestepstyle.core.dto.nhanvien.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 9/25/2024 21:14:54
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienRequest {

    private String hoTen;

    private String ngaySinh;

    private String diaChi;

    private Boolean gioiTinh;

    private String soDienThoai;

    private String email;

    private String ghiChu;

    private String anh;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private Long chucVu;
}
