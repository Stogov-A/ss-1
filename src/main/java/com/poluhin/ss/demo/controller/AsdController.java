package com.poluhin.ss.demo.controller;

import com.poluhin.ss.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class AsdController {
    private final UserService userService;

    @PostMapping
    public boolean login(@RequestBody AuthenticationReq req) {
        return userService.findFirstBySpecificRoles(req.getEmail());
    }

}
