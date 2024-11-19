package com.okconde.bestepstyle.core.config.security;

import com.okconde.bestepstyle.core.entity.KhachHang;
import com.okconde.bestepstyle.core.entity.NhanVien;
import com.okconde.bestepstyle.core.repository.KhachHangRepository;
import com.okconde.bestepstyle.core.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * Created by Trong Phu on 02/11/2024 17:32
 *
 * @author Trong Phu
 */
@Configuration
@RequiredArgsConstructor
public class SercurityConfig {
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<NhanVien> optionalNhanVien = nhanVienRepository.timNVTheoMaNV(username);
            Optional<KhachHang> optionalKhachHang = khachHangRepository.timKHTheoMaKH(username);
            if(optionalNhanVien.isPresent()) {
                return optionalNhanVien.get();
            }else if (optionalKhachHang.isPresent()){
                return optionalKhachHang.get();
            }else {
                throw new UsernameNotFoundException("Sai tài khoản hoặc mật khẩu");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
