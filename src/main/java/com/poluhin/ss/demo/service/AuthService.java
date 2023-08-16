package com.poluhin.ss.demo.service;

import com.poluhin.ss.demo.config.JwtService;
import com.poluhin.ss.demo.domain.entity.UserEntity;
import com.poluhin.ss.demo.domain.model.AuthenticationReq;
import com.poluhin.ss.demo.domain.model.AuthenticationResp;
import com.poluhin.ss.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtTokenService;
    private final UserRepository userRepository;

    public ResponseEntity<AuthenticationResp> login(AuthenticationReq req) {
        UserEntity user = (UserEntity) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())).getPrincipal();
        String token = jwtTokenService.generateToken(user);
        AuthenticationResp response = new AuthenticationResp(user.getId(), token);
        return ResponseEntity.ok(response);
    }
}
