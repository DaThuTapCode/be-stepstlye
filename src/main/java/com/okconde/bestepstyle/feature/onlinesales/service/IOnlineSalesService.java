package com.okconde.bestepstyle.feature.onlinesales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;

/**
 * Created by Trong Phu on 20/11/2024 00:17
 *
 * @author Trong Phu
 */
public interface IOnlineSalesService {
    /**
     * Tạo đơn hàng online vưới trường hợp mua ngay 1 sản phẩm*/
    HoaDonResponse taoDonHangOnlineMotSanPham(HoaDonBanOnlineRequest hoaDonBanOnlineRequest, String maKhachHang);
}
