package com.example.project.dto.user;

public record CustomerRegistrationDTO(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {}
