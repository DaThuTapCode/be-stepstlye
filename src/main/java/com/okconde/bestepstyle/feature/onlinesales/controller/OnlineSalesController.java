package com.okconde.bestepstyle.feature.onlinesales.controller;

import com.okconde.bestepstyle.core.config.auth.AuthenticationUtil;
import com.okconde.bestepstyle.feature.onlinesales.service.OnlineSalesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public OnlineSalesController(OnlineSalesService onlineSalesService) {
        this.onlineSalesService = onlineSalesService;
    }

    @GetMapping("/current-roles")
    public List<String> getCurrentUserRoles(
    ) {
        return AuthenticationUtil.getLoggedInUserRoles();
    }
}
