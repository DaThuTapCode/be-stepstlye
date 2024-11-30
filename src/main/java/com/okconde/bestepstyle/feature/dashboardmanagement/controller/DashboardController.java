package com.okconde.bestepstyle.feature.dashboardmanagement.controller;

import com.okconde.bestepstyle.core.dto.dashboard.request.DoanhThuNgayRequestDTO;
import com.okconde.bestepstyle.core.dto.dashboard.response.DoanhThuNgayResponseDTO;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.dashboardmanagement.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Trong Phu on 28/11/2024 23:34
 * @author Trong Phu
 */
@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("doanh-thu-ngay-hom-nay")
    public ResponseEntity<?> doanhThuNgayHomNay() {
        return ResponseEntity.ok(
          new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công doanh thu ngày", dashboardService.layDoanhThuNgay())
        );
    }

    @GetMapping("doanh-thu-thang-hien-tai")
    public ResponseEntity<?> doanhThuThangHienTai() {
        return ResponseEntity.ok(
          new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công doanh thu tháng hiện tại", dashboardService.layDoanhThuThangHienTai())
        );
    }
  @GetMapping("doanh-thu-nam-hien-tai")
    public ResponseEntity<?> doanhThuNamHienTai() {
        return ResponseEntity.ok(
          new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công doanh thu năm hiện tại", dashboardService.layDoanhThuNamHienTai())
        );
    }

    @GetMapping("/doanh-thu-cac-ngay-trong-thang-nam")
    public ResponseEntity<?> layDoanhThuThang(
            @RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam
    ) {
        List<DoanhThuNgayResponseDTO> doanhThu = dashboardService.layDoanhThuCacNgayTheoThangVaNam(thang, nam);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Lấy thành công doanh thu từng ngày", doanhThu)
        );
    }

}
