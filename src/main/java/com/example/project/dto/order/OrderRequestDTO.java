package com.example.project.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderRequestDTO(

        @NotNull(message = "Address cannot be null or empty")
        @Size(min = 3, max = 30, message = "Address must be between 3 and 30 characters")
        String address
) {
    public OrderRequestDTO
    {
        address = address.trim();
    }

}
