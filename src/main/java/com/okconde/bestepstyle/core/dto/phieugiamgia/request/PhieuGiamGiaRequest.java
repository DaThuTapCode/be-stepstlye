package com.okconde.bestepstyle.core.dto.phieugiamgia.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okconde.bestepstyle.core.util.enumutil.StatusLoaiGiam;
import com.okconde.bestepstyle.core.util.enumutil.StatusPhieuGiamGia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;


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

    private String maPhieuGiamGia;

    @NotBlank(message = "Tên PGG không được để trống!")
    @Length(min = 6, message = "Tên PGG phải lớn hơn 6 ký tự!")
    @Length(max = 255, message = "Tên PGG không được vượt quá 255 ký tự!")
    private String tenPhieuGiamGia;

    private Integer soLuong;

    private String moTa;

    @Enumerated(EnumType.STRING)
    private StatusLoaiGiam loaiGiam;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayBatDau;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayKetThuc;

    @NotNull(message = "Gia tri giảm tối đa không được để trống!")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm tối đa phải lớn hơn 0!")
    private BigDecimal giaTriGiamToiDa; // sửa về int

    @NotNull(message = "Gia tri giảm tối thiểu không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm tối thiểu phải lớn hơn 0!")
    private BigDecimal giaTriHoaDonToiThieu;

    @NotNull(message = "Gia tri giảm không được để trống!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gia tri giảm phải lớn hơn 0!")
    private BigDecimal giaTriGiam;

    @Enumerated(EnumType.STRING)
    private StatusPhieuGiamGia trangThai;

}
