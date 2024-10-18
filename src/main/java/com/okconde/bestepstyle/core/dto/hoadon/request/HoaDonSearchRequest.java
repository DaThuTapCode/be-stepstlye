package com.okconde.bestepstyle.core.dto.hoadon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * Created by TuanIf on 10/16/2024 16:11:12
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonSearchRequest {

    private String maHoaDon;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayTaoStart;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayTaoEnd;

    private Long idKhachHang;

    private Long idNhanVien;

    private Long idThanhToan;

    private Long idPhieuGiamGia;


}
