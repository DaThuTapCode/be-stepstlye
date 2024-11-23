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
}
