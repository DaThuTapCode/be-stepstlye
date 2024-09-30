package com.okconde.bestepstyle.core.dto.khachhang.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private List<DiaChiKhachHangShortResponse> diaChiKhachHangs;

}
