package com.okconde.bestepstyle.core.dto.sanpham.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Trong Phu on 08/10/2024 19:58
 * Đối tượng hứng dữ liệu tìm kiếm sản phẩm
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamSearchRequest {
    private String tenSanPham;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayTaoStart;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayTaoEnd;

    private Long idDanhMuc;

    private Long idThuongHieu;
}
