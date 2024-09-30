package com.okconde.bestepstyle.core.dto.chucvu.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import com.okconde.bestepstyle.core.dto.nhanvien.response.NhanVienShortResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Quang Minh on 9/25/2024 21:13:24
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVuResponse {

    private Long idChucVu;

    private String tenChucVu;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
