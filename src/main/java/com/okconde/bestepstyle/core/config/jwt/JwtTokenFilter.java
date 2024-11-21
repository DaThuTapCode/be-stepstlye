package com.okconde.bestepstyle.core.config.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
//import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trong Phu on 5/20/2024
 *
 * @author Trong Phu
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${api.prefix}")
    private String apiPrefix;

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            System.out.printf("\n-----------------------------------------\n");
            System.out.printf("Token: %s\n", request.getHeader("Authorization"));
            System.out.printf("HTTP METHOD: %s\n", request.getMethod());
            System.out.printf("-----------------------------------------\n");

            if (isByPassToken(request)) {
                filterChain.doFilter(request, response); // Cho phép đi qua
                return;
            }

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.printf("Token is missing or invalid.\n");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            final String token = authHeader.substring(7);
            if (token.isEmpty()) {
                System.out.printf("Token is empty.\n");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Empty token");
                return;
            }

            final String userName = jwtTokenUtil.extractUserName(token);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtTokenUtil.validateToke(token, userDetails)) {
                    System.out.printf("Token validated successfully for user: %s\n", userName);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    System.out.printf("Invalid token for user: %s\n", userName);
                }
            }

            filterChain.doFilter(request, response); // Cho phép đi qua

        } catch (Exception exception) {
            System.out.printf("Error occurred: %s\n", exception.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isByPassToken(@NonNull HttpServletRequest request) {
//        final List<Pair<String, String>> bypasstoken = Arrays.asList(
//                // Sản phẩm
////                 Đăng nhập
//                Pair.of(String.format("%s/employee-login/login", apiPrefix), "OPTIONS"),
//                Pair.of(String.format("%s/customer-login/login", apiPrefix), "POST"),
//                Pair.of(String.format("%s/thuong-hieu", apiPrefix), "POST"),
//                Pair.of(String.format("%s/danh-muc", apiPrefix), "POST"),
//                Pair.of("/images", "GET")
//                // Sản phẩm chi tiết
//                // Thương hiệu
//                // Danh mục
//                // Màu sắc
//                // Trọng lượng
//                // Chất liệu
//                // Hóa đơn
//                // Hóa đơn chi tiết
//                // Khách hàng
//                // Nhân viên
//        );
//        for (Pair<String, String> bypassToken : bypasstoken) {
//            if (request.getServletPath().contains(bypassToken.getLeft())
//                    && request.getMethod().equals((bypassToken.getRight()))
//            ) {
//                return true;
//            }
//        }
        return true;
    }

}
