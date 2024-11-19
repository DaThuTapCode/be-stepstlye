package com.okconde.bestepstyle.core.dto.hoadon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.LoaiHoaDon;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
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

    private String tenKhachHang;

    private String soDienThoai;

    @Enumerated(EnumType.STRING)
    private StatusHoaDon trangThai;

    @Enumerated(EnumType.STRING)
    private LoaiHoaDon loaiHoaDon;


}
