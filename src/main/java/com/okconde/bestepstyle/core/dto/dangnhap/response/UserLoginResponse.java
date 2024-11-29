package com.okconde.bestepstyle.core.dto.dangnhap.response;

import lombok.*;

/**
 * Created by Trong Phu on 17/11/2024 11:59
 *
 * @author Trong Phu
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponse {
    private Long id;
    private String fullName;
    private String userName;
    private String token;
    private String role;
}
