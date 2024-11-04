package com.okconde.bestepstyle.core.dto.hoadonchitiet.request;

import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * Created by TuanIf on 9/25/2024 21:13:49
 *
 * @author TuanIf
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDonChiTietRequest {

    private Long idHoaDonChiTiet;

    @NotNull(message = "Id hóa đơn không được để trống")
    private Long idHoaDon;

    @NotNull(message = "Id sản phẩm chi tiêt không được để trống")
    private Long idSpct;

    @NotNull(message = "Số lượng không được rỗng!")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int soLuong;

    private String maHoaDonChiTiet;

    private BigDecimal donGia;

    private BigDecimal tongTien;

    @Enumerated(EnumType.STRING)
    private StatusHoaDonChiTiet trangThai;

}
