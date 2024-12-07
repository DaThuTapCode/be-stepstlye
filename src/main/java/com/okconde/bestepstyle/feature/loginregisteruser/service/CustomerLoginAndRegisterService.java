package com.okconde.bestepstyle.feature.loginregisteruser.service;

import com.okconde.bestepstyle.core.config.jwt.JwtTokenUtil;
import com.okconde.bestepstyle.core.dto.dangnhap.request.CustomerRegisterRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.request.UserLoginRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.response.UserLoginResponse;
import com.okconde.bestepstyle.core.entity.ChucVu;
import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import com.okconde.bestepstyle.core.repository.NhanVienRepository;
import com.okconde.bestepstyle.core.util.enumutil.StatusEnum;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Trong Phu on 16/11/2024 20:41
 *
 * @author Trong Phu
 */

@Service
public class CustomerLoginAndRegisterService {
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public CustomerLoginAndRegisterService(KhachHangRepository khachHangRepository, NhanVienRepository nhanVienRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.timKHTheoMaKH(userLoginRequest.getUserName(), StatusEnum.ACTIVE);
        if (optionalKhachHang.isEmpty()) {
            throw new BusinessException("Sai tài khoản hoặc mật khẩu!");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUserName(), userLoginRequest.getPassword(), optionalKhachHang.get().getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return new UserLoginResponse().builder()
                .id(optionalKhachHang.get().getIdKhachHang())
                .userName(optionalKhachHang.get().getUsername())
                .fullName(optionalKhachHang.get().getTenKhachHang())
                .token(jwtTokenUtil.gennerateToken(optionalKhachHang.get()))
                .role(optionalKhachHang.get().getChucVu().getTenChucVu())
                .build();
    }

    public UserLoginResponse register(CustomerRegisterRequest customerRegisterRequest) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.timKHTheoMaKH(customerRegisterRequest.getUserName(), StatusEnum.ACTIVE);
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.timNVTheoMaNVVaTrangThai(customerRegisterRequest.getUserName(), StatusEnum.ACTIVE);
        if(optionalKhachHang.isEmpty() && optionalNhanVien.isEmpty()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang(customerRegisterRequest.getUserName());
            khachHang.setMatKhau(passwordEncoder.encode(customerRegisterRequest.getPassword()));
            khachHang.setChucVu(ChucVu.builder().idChucVu(3L).build());
            khachHang.setEmail(customerRegisterRequest.getEmail());
            khachHang.setSoDienThoai(customerRegisterRequest.getPhone());
            khachHang.setNgayTao(LocalDateTime.now());
            khachHang.setNgayChinhSua(LocalDateTime.now());
            khachHang.setGioiTinh(true);
            khachHang.setTrangThai(StatusEnum.ACTIVE);
            khachHangRepository.save(khachHang);
            return  null;
        }else {
            throw new BusinessException("Tên đăng nhập đã tồn tại!");
        }
    }



}
