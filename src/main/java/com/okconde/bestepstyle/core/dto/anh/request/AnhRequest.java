package com.okconde.bestepstyle.core.dto.anh.request;

import com.okconde.bestepstyle.core.entity.SanPhamChiTiet;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.*;

/**
 * Created by Trong Phu on 25/09/2024 21:35
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnhRequest {
    private String tenAnh;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private SanPhamChiTiet sanPhamChiTiet;
}
