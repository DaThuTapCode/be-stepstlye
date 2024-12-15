package com.okconde.bestepstyle.core.dto.dangnhap.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterRequest {

    private String userName;

    private String fullName;

    private String password;

    private String email;

    private String phone;

}
