package com.okconde.bestepstyle.core.dto.diachikhachhang.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.khachhang.response.KhachHangShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 9/25/2024 21:14:05
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaChiKhachHangResponse {

    private Long idDiaChiKhachHang;

    private String maDiaChiKhachHang;

    private String quocGia;

    private String thanhPho;

    private String huyen;

    private String maPhuongXa;

    private String xa;

    private String duong;

    private String soNha;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private KhachHangShortResponse khachHang;

}
