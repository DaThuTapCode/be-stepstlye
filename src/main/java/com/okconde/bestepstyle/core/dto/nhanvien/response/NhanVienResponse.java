package com.okconde.bestepstyle.core.dto.nhanvien.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.chucvu.response.ChucVuShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Quang Minh on 9/25/2024 21:15:04
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienResponse {

    private Long idNhanVien;

    private String maNhanVien;

    private String hoTen;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    private String diaChi;

    private Boolean gioiTinh;

    private String soDienThoai;

    private String email;

    private String ghiChu;

    private String anh;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private ChucVuShortResponse chucVu;
}
