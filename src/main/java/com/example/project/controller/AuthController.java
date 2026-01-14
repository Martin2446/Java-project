package com.example.project.controller;

import com.example.project.dto.user.CustomerRegistrationDTO;
import com.example.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRegistrationDTO dto) {
        authService.registerCustomer(dto);
        return ResponseEntity.ok("Customer registered successfully!");
    }
}