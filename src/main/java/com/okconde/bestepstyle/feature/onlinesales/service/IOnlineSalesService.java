package com.okconde.bestepstyle.feature.onlinesales.service;

import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.dto.phieugiamgia.response.PhieuGiamGiaResponse;

import java.util.List;

/**
 * Created by Trong Phu on 20/11/2024 00:17
 *
 * @author Trong Phu
 */
public interface IOnlineSalesService {
    /**
     * Tạo đơn hàng online vưới trường hợp mua ngay 1 sản phẩm*/
    HoaDonResponse taoDonHangOnlineMotSanPham(HoaDonBanOnlineRequest hoaDonBanOnlineRequest, String maKhachHang);

    /**
     * Kiểm tra số lượng sản phẩm truớc khi chuyển sang trang mua hàng
     * */
    void kiemTraSoLuongTruocKhiChuyenSangTrangMuaHang(HoaDonBanOnlineRequest hoaDonBanOnlineRequest, String maKhachHang);

    /**
     * Lấy danh sách phiếu giảm giá
     * */
    List<PhieuGiamGiaResponse> getDanhSachPhieuGiamGia();

    /**Tạo đơn hàng online*/
    HoaDonResponse taoDonHangOnline(HoaDonBanOnlineRequest hoaDonBanOnlineRequest);

    /**
     * Hủy đơn hàng online phía khách hàng
     * */
    HoaDonResponse huyHoaDonOnlinePhiaKhachHang(Long idHoaDon, String maKH, String lyDoHuy);

    /** Hủy hóa đơn phía admin*/
    HoaDonResponse huyHoaDonPhiaAdmin(Long idHoaDon, String maNV, String lyDoHuy);
}
