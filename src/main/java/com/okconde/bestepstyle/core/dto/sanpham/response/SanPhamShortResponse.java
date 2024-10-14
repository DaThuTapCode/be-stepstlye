package com.okconde.bestepstyle.core.dto.sanpham.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.entity.DanhMuc;
import com.okconde.bestepstyle.core.entity.ThuongHieu;
import com.okconde.bestepstyle.core.util.enumutil.StatusSP;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Trong Phu on 25/09/2024 21:20
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamShortResponse {
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
}
