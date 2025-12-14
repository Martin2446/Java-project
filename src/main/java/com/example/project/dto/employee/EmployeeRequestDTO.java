package com.example.project.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeRequestDTO(

        @NotBlank(message = "Employee first name cannot be null or empty")
        @Size(min = 2, max = 20, message = "Employee first name must be between 2 and 20 characters")
        String firstName,

        @NotBlank(message = "Employee last name cannot be null or empty")
        @Size(min = 2, max = 20, message = "Employee last name must be between 2 and 20 characters")
        String lastName,

        @Pattern(regexp = "\\d{10}", message = "If phone number is provided, it must be exactly 10 digits")
        String phoneNumber,

        @Size(min = 2, max = 25, message = "Department name must be between 2 and 25 characters")
        String departmentName
) {
    public EmployeeRequestDTO {
        firstName = firstName.trim();
        lastName = lastName.trim();
        phoneNumber = phoneNumber.trim();
        departmentName = departmentName.trim();
    }
}