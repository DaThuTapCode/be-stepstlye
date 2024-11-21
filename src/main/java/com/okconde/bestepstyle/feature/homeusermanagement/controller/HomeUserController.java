package com.okconde.bestepstyle.feature.homeusermanagement.controller;

import com.okconde.bestepstyle.core.config.jwt.JwtTokenUtil;
import com.okconde.bestepstyle.core.dto.hoadon.response.HoaDonResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.homeusermanagement.service.HomeUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Trong Phu on 21/11/2024 00:14
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping(value = "/api/home-user")
public class HomeUserController {
    private final HomeUserService homeUserService;
    private final JwtTokenUtil jwtTokenUtil;

    public HomeUserController(HomeUserService homeUserService, JwtTokenUtil jwtTokenUtil) {
        this.homeUserService = homeUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/lich-su-mua-hang")
    public ResponseEntity<ResponseData<List<HoaDonResponse>>> lichSuMuaHang(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String maKH = jwtTokenUtil.extractUserName(token);
        return ResponseEntity.ok(
                new ResponseData<>(HttpStatus.OK.value(), "Lấy lịch sử mua hàng thành công", homeUserService.getHoaDonTheoKhachHang(maKH))
        );
    }
}
