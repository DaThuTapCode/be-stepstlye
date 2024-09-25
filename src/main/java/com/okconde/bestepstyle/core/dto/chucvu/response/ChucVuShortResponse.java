package com.okconde.bestepstyle.core.dto.chucvu.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Quang Minh on 9/25/2024 21:20:05
 *
 * @author Quang Minh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVuShortResponse {

    private Long idChucVu;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;
}
