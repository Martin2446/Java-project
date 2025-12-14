package com.example.project.dto;

public record EmployeeResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        Long departmentId,
        String departmentName
) {}
