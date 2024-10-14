package com.okconde.bestepstyle.core.dto.kieudegiay.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Created at 25/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KieuDeGiayRequest {
    private Long idKieuDeGiay;

    private String maKieuDeGiay;

    //@NotBlank(message = "Tên kiểu đế giày không được để trống!")
    private String tenKieuDeGiay;

    private String giaTri;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusEnum trangThai;

}
