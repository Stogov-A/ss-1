package com.poluhin.ss.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResp {
    private String id;
    private String token;
}
