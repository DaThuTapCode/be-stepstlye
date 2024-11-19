package com.okconde.bestepstyle.feature.loginregisteruser.controller;

import com.okconde.bestepstyle.core.dto.dangnhap.request.UserLoginRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.response.UserLoginResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.loginregisteruser.service.EmployeeLoginAndRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Trong Phu on 16/11/2024 20:40
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping("api/employee-login")
public class EmployeeLoginAndRegisterController {

    private final EmployeeLoginAndRegisterService employeeLoginAndRegisterService;

    public EmployeeLoginAndRegisterController(EmployeeLoginAndRegisterService employeeLoginAndRegisterService) {
        this.employeeLoginAndRegisterService = employeeLoginAndRegisterService;
    }

    @PostMapping(value = "login")
    public ResponseEntity<ResponseData<UserLoginResponse>> employeeLogin(
            @RequestBody UserLoginRequest userLoginRequest) {

        /**
         * Kiểm tra đăng nhập và sinh token
         * */
        UserLoginResponse userLoginResponse = employeeLoginAndRegisterService.login(userLoginRequest);
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.ACCEPTED.value(),
                "Đăng nhập thành công!",
                userLoginResponse
        ));
    }
}
