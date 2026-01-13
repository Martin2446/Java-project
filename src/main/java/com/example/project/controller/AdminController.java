package com.example.project.controller;

import com.example.project.dto.user.AdminRegistrationDTO;
import com.example.project.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Management", description = "Endpoints for Global Admin to manage staff")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register-staff")
    public ResponseEntity<String> registerStaff(@Valid @RequestBody AdminRegistrationDTO dto) {
        adminService.registerStaff(dto);
        return new ResponseEntity<>("Staff registered successfully!", HttpStatus.CREATED);
    }
}