package com.poluhin.ss.demo.controller;

import com.poluhin.ss.demo.domain.model.AuthenticationReq;
import com.poluhin.ss.demo.domain.model.AuthenticationResp;
import com.poluhin.ss.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthenticationResp> login(@RequestBody AuthenticationReq req) {
        return authService.login(req);
    }

}
