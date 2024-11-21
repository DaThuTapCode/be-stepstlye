package com.okconde.bestepstyle.core.dto.sanpham.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.chatlieu.response.ChatLieuResponse;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucResponse;
import com.okconde.bestepstyle.core.dto.danhmuc.response.DanhMucShortResponse;
import com.okconde.bestepstyle.core.dto.thuonghieu.response.ThuongHieuResponse;
import com.okconde.bestepstyle.core.dto.trongluong.reponse.TrongLuongResponse;
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

    private String maSanPham;

    private String tenSanPham;

    private String moTa;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    private String nguoiTao;

    @Enumerated(EnumType.STRING)
    private StatusSP trangThai;

    private DanhMucResponse danhMuc;

    private ThuongHieuResponse thuongHieu;

    private ChatLieuResponse chatLieu;

    private TrongLuongResponse trongLuong;

    private List<SPCTShortResponse> sanPhamChiTiets;

}
