package com.okconde.bestepstyle.feature.dashboardmanagement.service;

import com.okconde.bestepstyle.core.dto.dashboard.response.DoanhThuNgayResponseDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Trong Phu on 28/11/2024 23:34
 * Cấu trúc dashboard
 * @author Trong Phu
 */
public interface IDashboardService  {
    /**
     *  Lấy doanh thu ngày hôm nay
     * */
    BigDecimal layDoanhThuNgay();

    /**
     * Lấy doanh thu tháng hiện tại
     * */
    BigDecimal layDoanhThuThangHienTai();

    /**
     * Lấy doanh thu năm hiện tại
     * */
    BigDecimal layDoanhThuNamHienTai();

    /**
     * Lấy danh sách doanh thu từng ngày của tháng và năm
     * */
    List<DoanhThuNgayResponseDTO> layDoanhThuCacNgayTheoThangVaNam(Integer thang, Integer nam);
}
