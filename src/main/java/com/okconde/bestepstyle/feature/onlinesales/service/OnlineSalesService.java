package com.okconde.bestepstyle.feature.onlinesales.service;

import com.okconde.bestepstyle.core.config.auth.AuthenticationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Trong Phu on 19/11/2024 20:59
 *
 * @author Trong Phu
 */
@Service
public class OnlineSalesService {
    public void printCurrentUserRoles() {
        List<String> roles = AuthenticationUtil.getLoggedInUserRoles();
        if (!roles.isEmpty()) {
            System.out.println("Roles của người dùng hiện tại: " + roles);
        } else {
            System.out.println("Người dùng hiện tại không có roles nào hoặc chưa đăng nhập.");
        }
    }

}
