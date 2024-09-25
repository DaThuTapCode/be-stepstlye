package com.okconde.bestepstyle.core.dto.sanpham.request;

import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 21:13
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamRequest {

    private String tenSanPham;

    private String moTa;

    private String nguoiTao;

    @Enumerated(EnumType.STRING)
    private StatusSP trangThai;

    private DanhMuc danhMuc;

    private ThuongHieu thuongHieu;

    private List<SanPhamChiTiet> sanPhamChiTiets;

}
