package com.okconde.bestepstyle.core.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Trong Phu on 17/11/2024 10:02
 *
 * @author Trong Phu
 */
public class AuthenticationUtil {
    /**
     * Lấy tên người dùng đăng nhập hiện tại
     */
    public static String getLoggedInUsername() {
        Authentication authentication = getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    /**
     * Lấy đối tượng UserDetails của người dùng hiện tại
     */
    public static UserDetails getLoggedInUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * Lấy danh sách roles (authorities) của người dùng hiện tại
     */
    public static List<String> getLoggedInUserRoles() {
        Authentication authentication = getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return List.of(); // Trả về danh sách rỗng nếu không có user đăng nhập
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority) // Chuyển đổi thành danh sách tên role
                .collect(Collectors.toList());
    }

    /**
     * Lấy đối tượng Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
