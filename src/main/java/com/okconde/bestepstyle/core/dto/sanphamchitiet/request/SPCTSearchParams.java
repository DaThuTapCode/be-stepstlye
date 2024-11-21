package com.okconde.bestepstyle.core.dto.sanphamchitiet.request;

import com.okconde.bestepstyle.core.entity.*;
import com.okconde.bestepstyle.core.util.enumutil.StatusSPCT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Trong Phu on 01/11/2024 09:01
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SPCTSearchParams {
    private String maSpct;

    private BigDecimal giaMin;

    private BigDecimal giaMax;

    private LocalDateTime ngayChinhSua;

    private StatusSPCT trangThai;

    private SanPham sanPham;

    private MauSac mauSac;

    private KichCo kichCo;
}
