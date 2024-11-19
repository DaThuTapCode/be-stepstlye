package com.okconde.bestepstyle.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

/**
 * Created by Trong Phu on 17/11/2024 10:32
 *
 * @author Trong Phu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Role role;

}
