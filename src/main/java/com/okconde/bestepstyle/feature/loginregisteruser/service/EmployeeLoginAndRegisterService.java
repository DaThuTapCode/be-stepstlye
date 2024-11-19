package com.okconde.bestepstyle.feature.loginregisteruser.service;

import com.okconde.bestepstyle.core.config.jwt.JwtTokenUtil;
import com.okconde.bestepstyle.core.dto.dangnhap.request.UserLoginRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.response.UserLoginResponse;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.exception.BusinessException;
import com.okconde.bestepstyle.core.repository.NhanVienRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Trong Phu on 16/11/2024 20:41
 *
 * @author Trong Phu
 */
@Service
public class EmployeeLoginAndRegisterService {
    private final NhanVienRepository nhanVienRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public EmployeeLoginAndRegisterService(NhanVienRepository nhanVienRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.nhanVienRepository = nhanVienRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
            Optional<NhanVien> optionalNhanVien = nhanVienRepository.timNVTheoMaNV(userLoginRequest.getUserName());
            if (optionalNhanVien.isEmpty()) {
                throw new BusinessException("Sai tài khoản hoặc mật khẩu!");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userLoginRequest.getUserName(), userLoginRequest.getPassword(), optionalNhanVien.get().getAuthorities()
            );
            authenticationManager.authenticate(authenticationToken);

            return new UserLoginResponse().builder()
                    .id(optionalNhanVien.get().getIdNhanVien())
                    .username(optionalNhanVien.get().getUsername())
                    .fullName(optionalNhanVien.get().getHoTen())
                    .token(jwtTokenUtil.gennerateToken(optionalNhanVien.get()))
                    .role(optionalNhanVien.get().getChucVu().getTenChucVu())
                    .build();
    }

}
