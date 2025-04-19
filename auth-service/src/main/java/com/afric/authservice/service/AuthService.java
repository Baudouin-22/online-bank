package com.afric.authservice.service;

import com.afric.authservice.model.User;
import com.afric.authservice.model.enums.Role;
import com.afric.authservice.repository.UserRepository;
import com.afric.common.dto.request.LoginRequest;
import com.afric.common.dto.request.RegisterRequest;
import com.afric.common.dto.response.AuthResponse;
import com.afric.common.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.getRoles().add(Role.valueOf("USER"));
        userRepo.save(user);
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("USER")))
        );
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(request.username());
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
