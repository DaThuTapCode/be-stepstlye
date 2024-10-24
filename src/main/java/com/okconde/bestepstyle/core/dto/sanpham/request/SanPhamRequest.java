package com.okconde.bestepstyle.core.dto.sanpham.request;

import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    private Long idSanPham;

    private String maSanPham;

    @Length(min = 4, max = 255, message = "Tên sản phẩm từ 4 đến 25 ký tự")
    private String tenSanPham;

    private String moTa;

    private String nguoiTao;

    @Enumerated(EnumType.STRING)
    private StatusSP trangThai;

    private DanhMucResponse danhMuc;

    private ThuongHieuResponse thuongHieu;

    private List<SPCTRequest> sanPhamChiTiets;

}
