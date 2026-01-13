package com.example.project.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AdminRegistrationDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String departmentName
) {}