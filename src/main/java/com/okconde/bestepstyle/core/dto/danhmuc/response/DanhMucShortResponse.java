package com.okconde.bestepstyle.core.dto.danhmuc.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Trong Phu on 25/09/2024 21:19
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanhMucShortResponse {
    private Long idDanhMuc;

    private String tenDanhMuc;

    private String moTa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayChinhSua;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
