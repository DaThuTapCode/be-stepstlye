package com.okconde.bestepstyle.feature.onlinesales.controller;

import com.okconde.bestepstyle.core.config.auth.AuthenticationUtil;
import com.okconde.bestepstyle.core.config.jwt.JwtTokenUtil;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonBanOnlineRequest;
import com.okconde.bestepstyle.core.dto.hoadon.request.HoaDonRequest;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.core.util.enumutil.StatusHoaDon;
import com.okconde.bestepstyle.feature.onlinesales.service.OnlineSalesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Trong Phu on 19/11/2024 20:55
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping(value = "api/online-sales")
public class OnlineSalesController {

    private final OnlineSalesService onlineSalesService;
    private final JwtTokenUtil jwtTokenUtil;
    public OnlineSalesController(OnlineSalesService onlineSalesService, JwtTokenUtil jwtTokenUtil) {
        this.onlineSalesService = onlineSalesService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/current-roles")
    public List<String> getCurrentUserRoles(
    ) {
        return AuthenticationUtil.getLoggedInUserRoles();
    }

    @PostMapping("/tao-hoa-don-mot-san-pham")
    public ResponseEntity<?> taoHoaDonChoDonHangMotSanPham(
            @RequestBody HoaDonBanOnlineRequest hoaDonBanOnlineRequest,
            HttpServletRequest request
            ) {
        if(request.getHeader("Authorization") == null){
            throw new BusinessException("Vui lòng đăng nhập để đặt hàng!");
        }
        String token = request.getHeader("Authorization").substring(7);
        String maKH = jwtTokenUtil.extractUserName(token);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Tạo đơn hàng thành công", onlineSalesService.taoDonHangOnlineMotSanPham(hoaDonBanOnlineRequest, maKH))
        );
    }

    @PostMapping("/tao-don-hang")
    public ResponseEntity<?> taoDonHang(
            @RequestBody HoaDonBanOnlineRequest hoaDonBanOnlineRequest,
            HttpServletRequest request
            ) {
        if(request.getHeader("Authorization") == null){
            throw new BusinessException("Vui lòng đăng nhập để đặt hàng!");
        }
        String token = request.getHeader("Authorization").substring(7);
        String maKH = jwtTokenUtil.extractUserName(token);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Tạo đơn hàng thành công", onlineSalesService.taoDonHangOnline(hoaDonBanOnlineRequest))
        );
    }

    @PostMapping("/check-so-luong-truoc-khi-chuyen-trang")
    public ResponseEntity<?> checkSoLuongTruocKhiChuyenTrang(
            @RequestBody HoaDonBanOnlineRequest hoaDonBanOnlineRequest,
            HttpServletRequest request
            ) {
        if(request.getHeader("Authorization") == null){
            throw new BusinessException("Vui lòng đăng nhập để đặt hàng!");
        }
        String token = request.getHeader("Authorization").substring(7);
        String maKH = jwtTokenUtil.extractUserName(token);
        onlineSalesService.kiemTraSoLuongTruocKhiChuyenSangTrangMuaHang(hoaDonBanOnlineRequest, maKH);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Chuyển trang thành công")
        );
    }

    @GetMapping("/lay-phieu-giam-gia-hoat-dong")
    public ResponseEntity<?> layPhieuGiamGiaHoatDong() {
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách phiếu giảm giá thành công", onlineSalesService.getDanhSachPhieuGiamGia())
        );
    }

    @PutMapping("/khach-hang-huy-hoa-don/{idHoaDon}")
    public ResponseEntity<?> khachHangHuyHoaDon(
            @PathVariable Long idHoaDon,
            @RequestParam String lyDoHuy,
            HttpServletRequest request
    ){
        String token = request.getHeader("Authorization").substring(7);
        String maKH = jwtTokenUtil.extractUserName(token);

        return ResponseEntity.ok(
          new ResponseData<>(HttpStatus.OK.value(),"Hủy hóa đơn thành công",onlineSalesService.huyHoaDonOnlinePhiaKhachHang(idHoaDon, maKH, lyDoHuy))
        );
    }

}
