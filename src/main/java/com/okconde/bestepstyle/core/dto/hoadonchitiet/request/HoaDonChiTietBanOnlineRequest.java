package com.okconde.bestepstyle.core.dto.hoadonchitiet.request;

import com.okconde.bestepstyle.core.dto.sanphamchitiet.request.SPCTRequest;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDonChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Trong Phu on 19/11/2024 23:08
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTietBanOnlineRequest {

    @NotNull(message = "Id sản phẩm chi tiêt không được để trống")
    private SPCTRequest sanPhamChiTiet;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer soLuong;

    private BigDecimal donGia;

}
