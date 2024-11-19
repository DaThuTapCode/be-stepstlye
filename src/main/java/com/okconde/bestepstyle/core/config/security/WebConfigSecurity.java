package com.okconde.bestepstyle.core.config.security;

import com.okconde.bestepstyle.core.config.jwt.JwtTokenFilter;

import com.okconde.bestepstyle.core.entity.ChucVu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Trong Phu on 5/21/2024
 *
 * @author Trong Phu
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfigSecurity {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(
                                        "/**",
                                        "/images/**,",
                                        "/api/customer-login/",
                                        "/api/employee-login/",
                                        "/api/online-sales/"
                                        )
                                .permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/api/san-pham/search", // Hỗ trợ đường dẫn tĩnh
                                        "/api/hoa-don/**",
                                        "/api/danh-muc/**",
                                        "/api/thuong-hieu/**",
                                        "/api/chat-lieu/**",
                                        "/api/chat-lieu-de-giay/**",
                                        "/api/kich-co/**",
                                        "/api/trong-luong/**",
                                        "/api/hoa-don-chi-tiet/**",
                                        "/api/online-sales/**",
                                        "/api/bhtq/**",
                                        "/api/san-pham-chi-tiet/**",
                                        "/api/san-pham/"
                                        ) // Hỗ trợ các query params sau search
                                .hasAnyRole( ChucVu.ADMIN, ChucVu.EMPLOYEE)
                                .requestMatchers(HttpMethod.GET,
                                        "/api/san-pham/search", // Hỗ trợ đường dẫn tĩnh
                                        "/api/hoa-don/**",
                                        "/api/danh-muc/**",
                                        "/api/thuong-hieu/**",
                                        "/api/chat-lieu/**",
                                        "/api/chat-lieu-de-giay/**",
                                        "/api/kich-co/**",
                                        "/api/trong-luong/**",
                                        "/api/hoa-don-chi-tiet/**",
                                        "/api/online-sales/**",
                                        "/api/bhtq/**",
                                        "/api/san-pham-chi-tiet/**",
                                        "/api/san-pham/")
                                .hasAnyRole(ChucVu.ADMIN, ChucVu.EMPLOYEE)
                );

        return http.build();
    }

}
