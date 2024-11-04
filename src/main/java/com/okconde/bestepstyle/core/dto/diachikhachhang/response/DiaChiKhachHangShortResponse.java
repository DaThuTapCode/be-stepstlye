package com.okconde.bestepstyle.core.dto.diachikhachhang.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 9/25/2024 21:40:08
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaChiKhachHangShortResponse {

    private Long idDiaChiKhachHang;

    private String maDiaChiKhachHang;

    private String idTinh;

    private String tenTinh;

    private String idQuanHuyen;

    private String tenQuanHuyen;

    private String maPhuongXa;

    private String tenPhuongXa;

    private String diaChiChiTiet;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
