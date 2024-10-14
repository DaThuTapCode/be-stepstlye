package com.okconde.bestepstyle.core.dto.khachhang.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.diachikhachhang.response.DiaChiKhachHangShortResponse;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    private Long idKhachHang;

    @NotBlank(message = "Mã khách hàng không được để trống!")
    @Length(min = 5, message = "Mã khách hàng phải lớn hơn 5 ký tự!")
    @Length(max = 10, message = "Mã khách hàng không được vượt quá 10 ký tự!")
    private String maKhachHang;

    @NotBlank(message = "Tên khách hàng không được để trống!")
    @Length(min = 6, message = "Tên khách hàng phải lớn hơn 6 ký tự!")
    @Length(max = 255, message = "Tên khách hàng không được vượt quá 255 ký tự!")
    private String tenKhachHang;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Length(min = 10, message = "Số điện thoại không được nhỏ hơn 10 ký tự!")
    @Length(max = 10, message = "Số điện thoại không được lớn hơn 10 ký tự!")
    private String soDienThoai;

    @NotBlank(message = "Email không được để trống!")
    @Length(max = 255, message = "Email không được vượt quá 255 ký tự!")
    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    private Boolean gioiTinh;

    private String ghiChu;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
