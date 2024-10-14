package com.okconde.bestepstyle.core.dto.diachikhachhang.request;

import com.okconde.bestepstyle.core.entity.KhachHang;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Quang Minh on 9/25/2024 21:13:50
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaChiKhachHangRequest {

    private Long idDiaChiKhachHang;

    @NotBlank(message = "Mã địa chỉ khách hàng không được để trống!")
    @Length(min = 7, message = "Mã địa chỉ khách hàng không được nhỏ hơn 7 ký tự!")
    @Length(max = 10, message = "Mã địa chỉ khách hàng không được lớn hơn 10 ký tự!")
    private String maDiaChiKhachHang;

    @NotBlank(message = "Quốc gia không được để trống!")
    @Length(min = 5, message = "Quốc gia không được nhỏ hơn 5 ký tự!")
    @Length(max = 255, message = "Quốc gia không được lớn hơn 255 ký tự!")
    private String quocGia;

    @NotBlank(message = "Thành phố không được để trống!")
    @Length(min = 5, message = "Thành phố không được nhỏ hơn 5 ký tự!")
    @Length(max = 255, message = "Thành phố không được lớn hơn 255 ký tự!")
    private String thanhPho;

    @NotBlank(message = "Huyện không được để trống!")
    @Length(min = 5, message = "Huyện không được nhỏ hơn 5 ký tự!")
    @Length(max = 255, message = "Huyện không được lớn hơn 255 ký tự!")
    private String huyen;

    @NotBlank(message = "Mã phường xã không được để trống!")
    @Length(min = 6, message = "Mã phường xã không được nhỏ hơn 6 ký tự!")
    @Length(max = 20, message = "Mã phường xã không được lớn hơn 255 ký tự!")
    private String maPhuongXa;

    @NotBlank(message = "Xã không được để trống!")
    @Length(min = 5, message = "Xã không được nhỏ hơn 5 ký tự!")
    @Length(max = 255, message = "Xã không được lớn hơn 255 ký tự!")
    private String xa;

    @NotBlank(message = "Đường không được để trống!")
    @Length(min = 5, message = "Đường không được nhỏ hơn 5 ký tự!")
    @Length(max = 255, message = "Đường không được lớn hơn 255 ký tự!")
    private String duong;

    @NotBlank(message = "Số nhà không được để trống!")
    @Length(min = 3, message = "Số nhà không được nhỏ hơn 3 ký tự!")
    @Length(max = 255, message = "Số nhà không được lớn hơn 255 ký tự!")
    private String soNha;

    private KhachHang khachHang;

}
