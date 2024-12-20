package com.okconde.bestepstyle.core.dto.sanphamchitiet.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonShortResponse;
import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import com.okconde.bestepstyle.core.dto.anh.response.AnhShortResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 25/09/2024 21:32
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SPCTShortResponse {

    private Long idSpct;

    private String maSpct;

    private BigDecimal gia;

    private int soLuong;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayTao;

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime ngayChinhSua;

    private String anh;

    @Enumerated(EnumType.STRING)
    private StatusSPCT trangThai;

    private MauSac mauSac;

    private KichCo kichCo;
}
