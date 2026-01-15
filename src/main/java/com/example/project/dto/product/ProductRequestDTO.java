package com.example.project.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public record ProductRequestDTO(

    @NotBlank(message = "Product name cannot be null or empty")
    @Size(min = 2, max = 40, message = "Product name must be between 2 and 40 characters")
    String name,

    @NonNull
    @Positive(message = "product price must be a positive number")
    double price,

    int amount
) {
    public ProductRequestDTO
    {
        name = name.trim();
    }
}
