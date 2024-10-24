package com.okconde.bestepstyle.core.dto.phieugiamgia.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/25/2024 21:14:40
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhieuGiamGiaRequest {

    private Long idPhieuGiamGia;

    @NotBlank(message = "Mã PGG không được để trống!")
    @Length(min = 5, message = "Mã PGG phải lớn hơn 5 ký tự!")
    @Length(max = 10, message = "Mã PGG không được vượt quá 10 ký tự!")
    private String maPhieuGiamGia;

    @NotBlank(message = "Tên PGG không được để trống!")
    @Length(min = 6, message = "Tên PGG phải lớn hơn 6 ký tự!")
    @Length(max = 255, message = "Tên PGG không được vượt quá 255 ký tự!")
    private String tenPhieuGiamGia;

    private String moTa;

    private String loaiGiam;

    @NotNull(message = "Gia tri giảm tối đa không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm tối đa > 0!")
    private BigDecimal giaTriGiamToiDa;

    @NotNull(message = "Gia tri giảm tối thiểu không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm tối thiểu > 0!")
    private BigDecimal giaTriGiamToiThieu;

    @NotNull(message = "Gia tri giảm không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm > 0!")
    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusPhieuGiamGia trangThai;

}
