package com.okconde.bestepstyle.feature.loginregisteruser.controller;

import com.okconde.bestepstyle.core.dto.dangnhap.request.CustomerRegisterRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.request.UserLoginRequest;
import com.okconde.bestepstyle.core.dto.dangnhap.response.UserLoginResponse;
import com.okconde.bestepstyle.core.objecthttp.ResponseData;
import com.okconde.bestepstyle.feature.loginregisteruser.service.CustomerLoginAndRegisterService;
import com.okconde.bestepstyle.feature.loginregisteruser.service.EmployeeLoginAndRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Trong Phu on 16/11/2024 20:40
 *
 * @author Trong Phu
 */
@RestController
@RequestMapping("api/customer-login")
public class CustomerLoginAndRegisterController {
    private final CustomerLoginAndRegisterService customerLoginAndRegisterService;

    public CustomerLoginAndRegisterController(CustomerLoginAndRegisterService customerLoginAndRegisterService) {
        this.customerLoginAndRegisterService = customerLoginAndRegisterService;
    }


    @PostMapping(value = "login")
    public ResponseEntity<ResponseData<UserLoginResponse>> customerLogin(
            @RequestBody UserLoginRequest userLoginRequest) {

        /**
         * Kiểm tra đăng nhập và sinh token
         * */
        UserLoginResponse userLoginResponse = customerLoginAndRegisterService.login(userLoginRequest);
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.ACCEPTED.value(),
                "Đăng nhập thành công!",
                userLoginResponse
        ));
    }

    @PostMapping(value = "register")
    public ResponseEntity<ResponseData<UserLoginResponse>> customerRegister(
            @RequestBody CustomerRegisterRequest customerRegisterRequest
    ) {

        /**
         * Kiểm tra đăng nhập và sinh token
         * */
        UserLoginResponse userLoginResponse = customerLoginAndRegisterService.register(customerRegisterRequest);
        return ResponseEntity.ok(new ResponseData(
                HttpStatus.ACCEPTED.value(),
                "Đăng nhập thành công!",
                userLoginResponse
        ));
    }
}
