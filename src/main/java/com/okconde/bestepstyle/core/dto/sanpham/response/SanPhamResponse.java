package com.okconde.bestepstyle.core.dto.sanpham.response;

import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import com.okconde.bestepstyle.core.dto.sanphamchitiet.response.SPCTShortResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
public class SanPhamResponse {

    private Long idSanPham;

    private String tenSanPham;

    private String moTa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayChinhSua;

    private String nguoiTao;

    @Enumerated(EnumType.STRING)
    private StatusSP trangThai;

    private DanhMuc danhMuc;

    private ThuongHieu thuongHieu;

    private List<SPCTShortResponse> sanPhamChiTiets;

}
