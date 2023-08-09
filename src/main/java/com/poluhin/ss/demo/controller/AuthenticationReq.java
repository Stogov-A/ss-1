package com.poluhin.ss.demo.controller;

import lombok.Data;

@Data
public class AuthenticationReq {
    private String email;
    private String password;
}
