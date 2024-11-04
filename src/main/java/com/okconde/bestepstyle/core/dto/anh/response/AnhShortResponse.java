package com.okconde.bestepstyle.core.dto.anh.response;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Created by Trong Phu on 25/09/2024 21:36
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnhShortResponse {
    private Long idAnh;

    private String tenAnh;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

    private int thu_tu;


}
