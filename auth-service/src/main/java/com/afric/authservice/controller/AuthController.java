package com.afric.authservice.controller;

import com.afric.authservice.model.User;
import com.afric.authservice.repository.UserRepository;
import com.afric.authservice.service.AuthService;
import com.afric.common.dto.request.LoginRequest;
import com.afric.common.dto.request.RegisterRequest;
import com.afric.common.dto.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public String test(){
        return "test value : ";
    }

    @GetMapping("/user")
    public List<User> getUser(){
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}