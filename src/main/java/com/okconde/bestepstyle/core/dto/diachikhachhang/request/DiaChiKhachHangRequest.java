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

    private String maDiaChiKhachHang;

    private String idTinh;

    private String tenTinh;

    private String idQuanHuyen;

    private String tenQuanHuyen;

    private String maPhuongXa;

    private String tenPhuongXa;

    private String diaChiChiTiet;

    private KhachHang khachHang;

}
