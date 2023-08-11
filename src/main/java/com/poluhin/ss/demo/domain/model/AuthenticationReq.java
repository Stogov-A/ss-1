package com.poluhin.ss.demo.domain.model;

import lombok.Data;

@Data
public class AuthenticationReq {
    private String username;
    private String password;
}
