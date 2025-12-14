package com.example.project.dto.employee;

public record EmployeeSummaryResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber
) {}