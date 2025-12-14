package com.example.project.dto;

public record EmployeeSummaryResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber
) {}