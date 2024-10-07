package com.okconde.bestepstyle.core.dto.nhanvien.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotBlank(message = "Tên nhân viên không được để trống!")
    @Length(min = 6, message = "Tên nhân viên phải lớn hơn 6 ký tự!")
    @Length(max = 255, message = "Tên nhân viên không được vượt quá 255 ký tự!")
    private String hoTen;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngaySinh;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Length(min = 20, message = "Địa chỉ phải lớn hơn 20 ký tự!")
    @Length(max = 500, message = "Địa chỉ khách hàng không được vượt quá 500 ký tự!")
    private String diaChi;

    private Boolean gioiTinh;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Length(min = 10, message = "Số điện thoại không được nhỏ hơn 10 ký tự!")
    @Length(max = 10, message = "Số điện thoại không được lớn hơn 10 ký tự!")
    private String soDienThoai;

    @NotBlank(message = "Email không được để trống!")
    @Length(max = 255, message = "Email không được vượt quá 255 ký tự!")
    private String email;

    private String ghiChu;

    private String anh;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

//    private Long chucVu;
}
