package com.johnsmith.springbootstudentmanagementsystem.controllers;

import com.johnsmith.springbootstudentmanagementsystem.mapper.UserMapper;
import com.johnsmith.springbootstudentmanagementsystem.payloads.requests.LoginRequest;
import com.johnsmith.springbootstudentmanagementsystem.payloads.requests.RegisterRequest;
import com.johnsmith.springbootstudentmanagementsystem.services.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userMapper.userToUserResponse(this.authService.register(request))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.login(request));
    }

}
