package com.okconde.bestepstyle.core.dto.diachikhachhang.request;

import com.okconde.bestepstyle.core.entity.KhachHang;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String quocGia;

    private String thanhPho;

    private String huyen;

    private String maPhuongXa;

    private String xa;

    private String duong;

    private String soNha;

    private KhachHang khachHang;

}
